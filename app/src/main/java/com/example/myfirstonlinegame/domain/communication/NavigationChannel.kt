package com.example.myfirstonlinegame.domain.communication

import kotlinx.serialization.Serializable

interface NavigationChannel {


    suspend fun subscribeToNavigationChannel(onNavigate: (Any) -> Unit, onGoBack  :() -> Unit)

    suspend fun navigate (route : Any)

    suspend fun navigateBack()
}