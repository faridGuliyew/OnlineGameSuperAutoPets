package com.example.myfirstonlinegame.ui.screens.lobby_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstonlinegame.data.remote.service.MatchmakingService
import com.example.myfirstonlinegame.domain.communication.NavigationChannel
import com.example.myfirstonlinegame.domain.manager.ServiceManager
import com.example.myfirstonlinegame.domain.tasks.lobby.TaskConnectToLobby
import com.example.myfirstonlinegame.domain.tasks.lobby.TaskLeaveLobby
import com.example.myfirstonlinegame.ui.screens.game_screen.GameRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val serviceManager: ServiceManager,
    private val navigationChannel : NavigationChannel,
    private val connectToLobbyTask: TaskConnectToLobby,
    private val leaveLobbyTask : TaskLeaveLobby
) : ViewModel() {

    val state = MutableStateFlow(LobbyScreenState())

    init {
        connectToLobby()
        viewModelScope.launch { state.collectLatest(::handleState) }
    }

    private fun bindToLobbyService() {
        viewModelScope.launch {
            onConnectedToLobbyService(serviceManager.bindToLobbyManagerService())
        }
    }

    private fun onConnectedToLobbyService (lobbyService: MatchmakingService) {
        lobbyService.startListeningForLobbyChanges(state.value.lobby!!) { newLobby->
            state.update { it.copy(lobby = newLobby) }
        }
    }

    private suspend fun handleState (state: LobbyScreenState) {
        state.lobby?.let {
            if (it.timeLeft == 0) {
                //Finish lobby and start the game
                serviceManager.unbindFromLobbyManagerService()
                navigationChannel.navigate(GameRoute)
            }
        }
    }

    private fun connectToLobby () {
        connectToLobbyTask(
            param = Unit,
            scope = viewModelScope,
            onFailure = {
                Log.e("Lobby", it.message.orEmpty())
            }
        ) { result->
            Log.i("Lobby", "Connection success: $result")
            state.update { it.copy(lobby = result) }
            bindToLobbyService()
        }
    }

    fun leaveLobby() {
        leaveLobbyTask(
            param = state.value.lobby!!,
            scope = viewModelScope,
            onFailure = {
                Log.e("Lobby", it.message.orEmpty())
            },
            onSuccess = {
                serviceManager.unbindFromLobbyManagerService()
                navigationChannel.navigateBack()
            }
        )
    }

}