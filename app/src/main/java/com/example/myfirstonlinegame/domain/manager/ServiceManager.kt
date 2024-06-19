package com.example.myfirstonlinegame.domain.manager

import com.example.myfirstonlinegame.data.remote.service.MatchmakingService

/**
 * Responsible for start/stopping services
 * */
interface ServiceManager {

    suspend fun bindToLobbyManagerService() : MatchmakingService

    fun unbindFromLobbyManagerService()

}