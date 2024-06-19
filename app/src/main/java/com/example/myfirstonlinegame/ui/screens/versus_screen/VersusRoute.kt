package com.example.myfirstonlinegame.ui.screens.versus_screen

import androidx.compose.runtime.Composable
import com.example.myfirstonlinegame.ui.navigation.core.LocalNavController
import com.example.myfirstonlinegame.ui.screens.lobby_screen.LobbyRoute
import kotlinx.serialization.Serializable

@Serializable
data object VersusRoute

sealed interface VersusScreenActions {
    data object OnPlay : VersusScreenActions
}

@Composable
fun VersusRoute() {
    val navController = LocalNavController.current
    VersusScreen(
        onAction = {
            when(it) {
                is VersusScreenActions.OnPlay -> {
                    navController.navigate(LobbyRoute)
                }
            }
        }
    )
}