package com.example.myfirstonlinegame.ui.screens.game_screen.extensions

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.myfirstonlinegame.ui.screens.game_screen.MainPetSlot
import com.example.myfirstonlinegame.ui.screens.game_screen.Pet

/**
 * This function moves pet to the specified slot and hides arrows. No other logic
 * */
fun Pet?.moveToSlot(newPosition : Int, slots : List<MainPetSlot>) {
    if (this == null) return
    slots[position].isEmpty = true
    position = newPosition
    slots[newPosition].isEmpty = false
    slots.hideAllArrows()
}

/**
 * This functions ONLY removes pet, sets slot to empty and hides arrows
 * */
fun SnapshotStateList<Pet>.sell(pet : Pet, slots: List<MainPetSlot>) {
    this.remove(pet)
    slots[pet.position].isEmpty = true
    slots.hideAllArrows()
}

