package com.example.myfirstonlinegame.firebase.collections

import com.google.firebase.firestore.FirebaseFirestore

object CollectionPublicLobbies {

    const val collectionName = "public_lobbies"

    object Fields {
        const val PLAYERS = "players"
        const val CURRENT_PLAYER_COUNT = "currentPlayerCount"
        const val MAX_PLAYER_COUNT = "maxPlayerCount"
        const val LOBBY_FULL = "lobbyFull"
        const val LOBBY_MANAGER_ID = "lobbyManagerId"
        const val TIME_LEFT = "timeLeft"
    }

}

val FirebaseFirestore.collectionPublicLobbies get() = collection(CollectionPublicLobbies.collectionName)
fun FirebaseFirestore.publicLobby (id : String) = collectionPublicLobbies.document(id)