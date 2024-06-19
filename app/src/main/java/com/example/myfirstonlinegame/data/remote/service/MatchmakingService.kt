package com.example.myfirstonlinegame.data.remote.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.myfirstonlinegame.domain.services.Lobby
import com.example.myfirstonlinegame.domain.services.PlayerService
import com.example.myfirstonlinegame.firebase.collections.CollectionPublicLobbies
import com.example.myfirstonlinegame.firebase.collections.publicLobby
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.getField
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MatchmakingService : Service() {

    /**
     * These steps are necessary for clients to bind to the service and communicate.
     * */
    private val binder = LobbyManagerServiceBinder()

    inner class LobbyManagerServiceBinder : Binder() {
        fun getService() = this@MatchmakingService
    }

    override fun onBind(intent: Intent): IBinder {
        Log.i("LobbyManagerService", "Service bound")
        return binder
    }

    private val scope = CoroutineScope(Dispatchers.Main)

    @Inject
    lateinit var firestore: FirebaseFirestore

    @Inject
    lateinit var playerService: PlayerService

    private var isManagementActive = false

    fun startListeningForLobbyChanges(
        lobby: Lobby,
        onLobbyChange : (Lobby) -> Unit
    ) {
        val listener = listener@{ value: DocumentSnapshot?, error: FirebaseFirestoreException? ->
            if (error != null) {
                Log.e("LobbyManagerService", "Listen failed.", error)
                TODO("Leave lobby, error happened")
                return@listener
            }
            val newLobby = value?.toObject(Lobby::class.java) ?: return@listener
            onLobbyChange(newLobby)
            scope.launch { manageLobbyIfNeeded(lobby) }
        }

        firestore.publicLobby(lobby.id).addSnapshotListener(listener)
    }


    //The rest of this file is about management. If the current user is not a manager, skip this part
    private suspend fun manageLobbyIfNeeded(lobby: Lobby) {
        if (isManagementActive) return
        if (playerService.getCurrentPlayer().id != lobby.lobbyManagerId) {
            isManagementActive = false
            return
        }

        isManagementActive = true
        Log.i("LobbyManagerService", "Management activated!")
        startLobbyManagement(lobby)
    }

    private suspend fun startLobbyManagement(
        lobby: Lobby
    ) {
        while (lobby.timeLeft > 0) {
            coroutineContext.ensureActive()
            runCatching {
                delay(500)
                Log.e("LobbyManagerService", "Start lower timer task")
                val timeLeft = firestore.publicLobby(lobby.id).get().await().getField<Int>(
                    CollectionPublicLobbies.Fields.TIME_LEFT
                ) ?: return@runCatching

                delay(500)
                firestore.publicLobby(lobby.id)
                    .update(CollectionPublicLobbies.Fields.TIME_LEFT, timeLeft - 1)
                    .await()
                Log.e("LobbyManagerService", "Lowered timer")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LobbyManagerService", "onDestroy")
        scope.coroutineContext.cancelChildren()
    }
}