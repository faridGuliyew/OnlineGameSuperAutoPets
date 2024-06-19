package com.example.myfirstonlinegame.domain.tasks.lobby

import com.example.myfirstonlinegame.base.Task
import com.example.myfirstonlinegame.domain.services.Lobby
import com.example.myfirstonlinegame.domain.services.LobbyService
import javax.inject.Inject

class TaskLeaveLobby @Inject constructor(
    private val lobbyService: LobbyService
): Task<Lobby, Unit>() {
    override suspend fun run(param: Lobby) {
        lobbyService.disconnect(param)
    }
}