package com.example.myfirstonlinegame.domain.tasks.lobby

import com.example.myfirstonlinegame.base.Task
import com.example.myfirstonlinegame.domain.services.Lobby
import com.example.myfirstonlinegame.domain.services.LobbyService
import javax.inject.Inject

class TaskConnectToLobby @Inject constructor(
    private val lobbyService: LobbyService
) : Task<Unit, Lobby>() {
    override suspend fun run(param: Unit): Lobby {
        return lobbyService.connect()
    }
}