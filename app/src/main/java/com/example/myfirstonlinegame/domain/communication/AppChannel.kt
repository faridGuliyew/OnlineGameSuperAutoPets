package com.example.myfirstonlinegame.domain.communication

import kotlinx.serialization.Serializable

interface AppChannel {

    suspend fun subscribeToLoadingChannel(onIsLoadingChange: (Boolean) -> Unit)

    suspend fun setLoading (isLoading : Boolean)
}