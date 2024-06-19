package com.example.myfirstonlinegame.ui.screens.lobby_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfirstonlinegame.domain.services.Lobby
import com.example.myfirstonlinegame.ui.screens.versus_screen.VersusScreen
import kotlinx.serialization.Serializable

@Serializable
data object LobbyRoute

data class LobbyScreenState (
    val lobby: Lobby? = null
)

sealed interface LobbyScreenActions {
    data object OnLeave : LobbyScreenActions
}

@Composable
fun LobbyRoute() {
    val viewModel = hiltViewModel<LobbyViewModel>()
    val screenState by viewModel.state.collectAsStateWithLifecycle()

    LobbyScreen(
        screenState = screenState
    ) {
        when (it) {
            LobbyScreenActions.OnLeave -> viewModel.leaveLobby()
        }
    }
}