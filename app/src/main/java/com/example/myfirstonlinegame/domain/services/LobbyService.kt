package com.example.myfirstonlinegame.domain.services

import com.google.firebase.firestore.PropertyName
import java.util.UUID
import kotlin.jvm.Throws

data class Lobby(
    @PropertyName("id")
    val id: String = UUID.randomUUID().toString(),
    @PropertyName("lobbyManagerId")
    val lobbyManagerId : String = "",
    @PropertyName("currentPlayerCount")
    val currentPlayerCount : Int = 0,
    @PropertyName("players")
    val players: List<Player> = emptyList(),
    @PropertyName("lobbyFull")
    val lobbyFull : Boolean = false,
    //some settings go here
    @PropertyName("maxPlayerCount")
    val maxPlayerCount : Int = 8,
    //lobby state goes here
    @PropertyName("timeLeft")
    val timeLeft : Int = 30
)

/**
 * This service is responsible for connecting to and disconnecting from the lobbies, and setting a manager
 * */
interface LobbyService {

    /**
     * Function to connect to a lobby
     * If there is no available lobby, creates a new one
     * @return lobby object
     * @throws LobbyException
     * */
    suspend fun connect() : Lobby

    /**
     * Function to disconnect from a lobby
     * @throws LobbyException
     * */
    suspend fun disconnect(lobby: Lobby)
}

class LobbyException (reason : String?) : Exception("Lobby service failed: $reason")