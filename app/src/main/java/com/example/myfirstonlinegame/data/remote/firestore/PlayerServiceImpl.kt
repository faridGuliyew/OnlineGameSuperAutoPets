package com.example.myfirstonlinegame.data.remote.firestore

import com.example.myfirstonlinegame.domain.services.Player
import com.example.myfirstonlinegame.domain.services.PlayerService
import java.util.UUID
import javax.inject.Inject

class PlayerServiceImpl @Inject constructor(): PlayerService {

    //Cached current player object, to avoid calling getCurrentPlayer() every time
    private var cachedCurrentPlayer : Player? = null

    /**
     * Function to get the current player
     * TODO log out if it fails
     * */
    override suspend fun getCurrentPlayer(): Player {
        return /*cachedCurrentPlayer ?:*/ Player("STATIC", "Player1").also { cachedCurrentPlayer = it }
    }
}