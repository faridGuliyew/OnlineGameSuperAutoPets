package com.example.myfirstonlinegame.ui.navigation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.myfirstonlinegame.ui.navigation.sub_navigation.MainNavigation
import com.example.myfirstonlinegame.ui.navigation.sub_navigation.mainNavigation

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    CompositionLocalProvider(value = LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = MainNavigation) {
            mainNavigation()
        }
    }
}

val LocalNavController = compositionLocalOf<NavController> {
    error("NavController should be provided up in the tree")
}