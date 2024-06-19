package com.example.myfirstonlinegame.core

import android.util.Log
import com.example.myfirstonlinegame.domain.communication.NavigationChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

class NavigationChannelImpl @Inject constructor() : NavigationChannel {

    private val navigationChannel = MutableSharedFlow<Any>()
    private val navigateBackChannel = MutableSharedFlow<Unit>()

    override suspend fun subscribeToNavigationChannel(
        onNavigate: (Any) -> Unit,
        onGoBack: () -> Unit,
    ) {
        coroutineScope {
            launch {
                Log.i("Navigation", "Navigate collector is listening")
                navigationChannel.collectLatest {
                    Log.i("Navigation", "Navigate sent to listener")
                    onNavigate(it)
                }
            }
            launch {
                Log.i("Navigation", "Navigate back collector is listening")
                navigateBackChannel.collectLatest {
                    Log.i("Navigation", "Navigate back sent to listener")
                    onGoBack()
                }
            }
        }
    }

    override suspend fun navigate(route: Any) {
        navigationChannel.emit(route)
    }

    override suspend fun navigateBack() {
        navigateBackChannel.emit(Unit)
    }

}