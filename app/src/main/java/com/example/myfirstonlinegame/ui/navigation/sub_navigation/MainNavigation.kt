package com.example.myfirstonlinegame.ui.navigation.sub_navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.myfirstonlinegame.ui.screens.game_screen.GameRoute
import com.example.myfirstonlinegame.ui.screens.home_screen.HomeRoute
import com.example.myfirstonlinegame.ui.screens.lobby_screen.LobbyRoute
import com.example.myfirstonlinegame.ui.screens.play_screen.PlayRoute
import com.example.myfirstonlinegame.ui.screens.versus_screen.VersusRoute
import kotlinx.serialization.Serializable

@Serializable
data object MainNavigation

fun NavGraphBuilder.mainNavigation() {
    navigation<MainNavigation>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeRoute()
        }
        composable<PlayRoute> {
            PlayRoute()
        }
        composable<VersusRoute> {
            VersusRoute()
        }
        composable<LobbyRoute> {
            LobbyRoute()
        }
        composable<GameRoute> {
            GameRoute()
        }
    }
}
