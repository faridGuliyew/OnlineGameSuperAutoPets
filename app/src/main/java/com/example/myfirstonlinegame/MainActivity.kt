package com.example.myfirstonlinegame

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.myfirstonlinegame.core.AppChannelImpl
import com.example.myfirstonlinegame.domain.communication.NavigationChannel
import com.example.myfirstonlinegame.ui.components.core.LoadingDialog
import com.example.myfirstonlinegame.ui.navigation.core.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationChannel: NavigationChannel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var isLoading by mutableStateOf(false)
        setContent {
            val navController = rememberNavController()
            AppNavigation(navController)
            LoadingDialog(isLoading = isLoading)
            LaunchedEffect(key1 = Unit) {
                navigationChannel.subscribeToNavigationChannel (
                    onNavigate =  navController::navigate,
                    onGoBack = { navController.navigateUp().also { Log.i("Navigation", "Navigate back!") } }
                )
            }
        }




        lifecycleScope.launch {
            AppChannelImpl.subscribeToLoadingChannel {
                isLoading = it
            }
        }
    }
}