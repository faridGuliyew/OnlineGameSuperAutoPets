package com.example.myfirstonlinegame.ui.screens.home_screen

import androidx.compose.runtime.Composable
import com.example.myfirstonlinegame.ui.navigation.core.LocalNavController
import com.example.myfirstonlinegame.ui.screens.play_screen.PlayRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

sealed interface HomeScreenActions {
    data object OnPlay : HomeScreenActions
}

@Composable
fun HomeRoute() {
    val navController = LocalNavController.current
    HomeScreen(
        onAction = {
            when(it) {
                HomeScreenActions.OnPlay -> {
                    navController.navigate(PlayRoute)
                }
            }
        }
    )
}