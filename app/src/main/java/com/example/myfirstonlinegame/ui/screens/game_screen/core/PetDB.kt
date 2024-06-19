package com.example.myfirstonlinegame.ui.screens.game_screen.core

import androidx.compose.ui.util.fastForEachIndexed
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.screens.game_screen.Pet

object PetDB {

    private val pets = listOf(
        Pet(
            title = "CRICKET",
            info = "Summon one 1/1 Cricket when is killed",
            image = R.drawable.cricket,
            initialHealth = 2,
            initialAttack = 3,

            round = 0
        ),
        Pet(
            title = "DUCK",
            info = "Give shop pets +1 health when sold.",
            image = R.drawable.duck,
            initialHealth = 3,
            initialAttack = 3,

            round = 0
        ),
        Pet(
            title = "HORSE",
            info = "Give a pet +1 attack when bought.",
            image = R.drawable.horse,
            initialHealth = 1,
            initialAttack = 2,

            round = 0
        ),Pet(
            title = "SWAN",
            info = "Gives +1 extra coin each round.",
            image = R.drawable.swan,
            initialHealth = 3,
            initialAttack = 2,

            round = 0
        ),Pet(
            title = "PIG",
            info = "Gives +1 coin when sold.",
            image = R.drawable.pig,
            initialHealth = 1,
            initialAttack = 4,

            round = 0
        ),Pet(
            title = "PIGEON",
            info = "Gives +1 bread crumb when sold.",
            image = R.drawable.pigeon,
            initialHealth = 2,
            initialAttack = 3,

            round = 0
        ),Pet(
            title = "PARROT",
            info = "Copy the ability of the nearest pet in front as level 1.",
            image = R.drawable.parrot,
            initialHealth = 3,
            initialAttack = 4,

            round = 0
        ),Pet(
            title = "TIGER",
            info = "The nearest pet repeats its ability as level 1.",
            image = R.drawable.tiger,
            initialHealth = 3,
            initialAttack = 6,

            round = 0
        ),Pet(
            title = "MONKEY",
            info = "The pet in front gets +2 health and +3 attack each round.",
            image = R.drawable.monkey,
            initialHealth = 2,
            initialAttack = 1,

            round = 0
        ),
    )


    fun getRandomPets (count: Int): List<Pet> {
        //Take $count random pets, copy the item so the references are different, and assign random positions
        return buildList {
            repeat(count) { iteration->
                add(pets.random().copy().apply { position = iteration })
            }
        }
    }
}