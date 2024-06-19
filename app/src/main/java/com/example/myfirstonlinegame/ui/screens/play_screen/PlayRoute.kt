package com.example.myfirstonlinegame.ui.screens.play_screen

import androidx.compose.runtime.Composable
import com.example.myfirstonlinegame.ui.navigation.core.LocalNavController
import com.example.myfirstonlinegame.ui.screens.versus_screen.VersusRoute
import kotlinx.serialization.Serializable


@Serializable
data object PlayRoute

sealed interface PlayScreenActions {
    data object OnVersus : PlayScreenActions
}

@Composable
fun PlayRoute() {
    val navController = LocalNavController.current
    PlayScreen(
        onAction = {
            when (it) {
                PlayScreenActions.OnVersus -> navController.navigate(VersusRoute)
            }
        }
    )
}