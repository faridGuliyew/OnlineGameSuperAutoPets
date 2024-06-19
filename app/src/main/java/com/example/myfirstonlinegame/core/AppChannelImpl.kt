package com.example.myfirstonlinegame.core

import com.example.myfirstonlinegame.domain.communication.AppChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This channel is used to communicate back to activity from any point of the app
 * */
object AppChannelImpl : AppChannel {

    private val isLoadingChannel = MutableSharedFlow<Boolean>()

    private val toastChannel = MutableSharedFlow<String>()

    override suspend fun subscribeToLoadingChannel(
        onIsLoadingChange: (Boolean) -> Unit
    ) {
        isLoadingChannel.collectLatest {
            onIsLoadingChange(it)
        }
    }

    override suspend fun setLoading (isLoading : Boolean) {
        isLoadingChannel.emit(isLoading)
    }
}