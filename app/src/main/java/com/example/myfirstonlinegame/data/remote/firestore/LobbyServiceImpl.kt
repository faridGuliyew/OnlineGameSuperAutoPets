package com.example.myfirstonlinegame.data.remote.firestore

import android.util.Log
import androidx.compose.ui.util.fastFilter
import com.example.myfirstonlinegame.domain.services.Lobby
import com.example.myfirstonlinegame.domain.services.LobbyException
import com.example.myfirstonlinegame.domain.services.LobbyService
import com.example.myfirstonlinegame.domain.services.PlayerService
import com.example.myfirstonlinegame.firebase.collections.CollectionPublicLobbies
import com.example.myfirstonlinegame.firebase.collections.collectionPublicLobbies
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LobbyServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val playerService: PlayerService,
) : LobbyService {

    /**
     * Function to create a lobby
     * @throws LobbyException if operation fails
     * */
    private suspend fun createLobby() : Lobby {
        val lobby = Lobby()
        return suspendCoroutine { cont->
            firestore.collectionPublicLobbies
                .document(lobby.id)
                .set(lobby)
                .addOnSuccessListener {
                    Log.i("Lobby", "Created a lobby with id ${lobby.id}")
                    cont.resume(lobby)
                }
                .addOnFailureListener {
                    throw LobbyException("All lobbies are full, Failed to create a new lobby")
                }
        }
    }

    //This function is called every time you connect. It adds the current player to the lobby
    private suspend fun addUserToLobby (lobby: Lobby) : Lobby {
        val playersAfterAddition = lobby.players.plus(playerService.getCurrentPlayer()).toSet()
        try {
            firestore.collectionPublicLobbies
                .document(lobby.id)
                .update(
                    mapOf<String, Any>(
                        CollectionPublicLobbies.Fields.PLAYERS to FieldValue.arrayUnion(playerService.getCurrentPlayer()),
                        CollectionPublicLobbies.Fields.CURRENT_PLAYER_COUNT to playersAfterAddition.size,
                        CollectionPublicLobbies.Fields.LOBBY_FULL to (lobby.maxPlayerCount <= playersAfterAddition.size)
                    )
                ).await()
        } catch (e:Exception) {
            throw LobbyException(e.message)
        }
        Log.i("Lobby", "Added player to the lobby ${lobby.id}! Players: $playersAfterAddition")
        return lobby.copy(players = playersAfterAddition.toList())
    }

    private suspend fun assignLobbyManagerOnConnect(lobby: Lobby) : Lobby {
        if (lobby.lobbyManagerId.isNotEmpty()) return lobby
        assignLobbyManager(lobby.players.first().id, lobby.id)
        Log.i("Lobby", "Lobby manager assigned!")
        return lobby.copy(lobbyManagerId = lobby.players.first().id)
    }

    private suspend fun assignLobbyManager(managerId : String, lobbyId: String) {
        try {
            firestore.collectionPublicLobbies
                .document(lobbyId)
                .update(
                    mapOf<String, Any>(
                        CollectionPublicLobbies.Fields.LOBBY_MANAGER_ID to managerId
                    )
                ).await()
        } catch (e:Exception) {
            throw LobbyException(e.message)
        }
    }

    //High level function that makes use of other functions to connect to a lobby
    override suspend fun connect(): Lobby {
        val availableLobbies = suspendCoroutine { cont->
            firestore.collectionPublicLobbies
                .whereEqualTo(CollectionPublicLobbies.Fields.LOBBY_FULL, false)
                .get()
                .addOnSuccessListener {
                    val lobbies = it.toObjects(Lobby::class.java)
                    Log.i("Lobby", "Lobbies: $lobbies")
                    cont.resume(lobbies)
                }.addOnFailureListener {
                    throw LobbyException(it.message)
                }
        }
        val lobbyToConnect =
            if (availableLobbies.isNotEmpty()) availableLobbies.first().also { Log.i("Lobby", "Connecting to an existing lobby") }
            else createLobby().also { Log.i("Lobby", "Connecting to a Fresh lobby") }
        val connectedLobby = addUserToLobby(lobbyToConnect)
        return assignLobbyManagerOnConnect(connectedLobby)
    }


    private suspend fun assignLobbyManagerOnDisconnect(lobby: Lobby) {
        //check if the current player is the manager of the lobby
        val currentPlayerId = playerService.getCurrentPlayer().id
        if (lobby.lobbyManagerId != currentPlayerId) return
        assignLobbyManager(lobby.players.fastFilter { it.id != currentPlayerId }.first().id, lobby.id)
        Log.i("Lobby", "Lobby manager reassigned!")
    }

    private suspend fun deleteLobby(lobby: Lobby) {
        try {
            firestore.collectionPublicLobbies
                .document(lobby.id)
                .delete().await()
        } catch (e:Exception) {
            throw LobbyException(e.message)
        }
    }

    //This function is called every time you disconnect. It removes the current player from the lobby
    private suspend fun removeUserFromLobby (lobby: Lobby) {
        try {
            firestore.collectionPublicLobbies
                .document(lobby.id)
                .update(
                    mapOf<String, Any>(
                        CollectionPublicLobbies.Fields.PLAYERS to FieldValue.arrayRemove(playerService.getCurrentPlayer()),
                        CollectionPublicLobbies.Fields.CURRENT_PLAYER_COUNT to lobby.currentPlayerCount - 1,
                        CollectionPublicLobbies.Fields.LOBBY_FULL to false
                    )
                ).await()
        } catch (e:Exception) {
            throw LobbyException(e.message)
        }
        Log.i("Lobby", "Removed player from the lobby ${lobby.id}!")
    }

    //High level function that makes use of other functions to disconnect from a lobby
    override suspend fun disconnect(lobby: Lobby) {
        if (lobby.players.size <= 1) return deleteLobby(lobby)
        assignLobbyManagerOnDisconnect(lobby)
        removeUserFromLobby(lobby)
    }
}