package com.example.myfirstonlinegame.domain.services

data class Player (
    val id: String = "",
    val username: String = "unknown",
    val score : Int = 0
)
/**
 * This service is responsible for keeping track of player state
 * */
interface PlayerService {

    suspend fun getCurrentPlayer() : Player

}