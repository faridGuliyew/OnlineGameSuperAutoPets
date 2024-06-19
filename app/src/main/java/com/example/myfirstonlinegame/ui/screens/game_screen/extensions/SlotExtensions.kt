package com.example.myfirstonlinegame.ui.screens.game_screen.extensions

import com.example.myfirstonlinegame.ui.screens.game_screen.MainPetSlot

/**
 * This function only makes arrows invisible
 * */
fun List<MainPetSlot>.hideAllArrows() {
    forEach { it.isArrowVisible = false }
}

/**
 * This function only makes empty slot arrows visible
 * */
fun List<MainPetSlot>.displayEmptySlotArrows() {
    forEach { if (it.isEmpty) it.isArrowVisible = true }
}

/**
 * This function makes all slot arrows visible regardless
 * */
fun List<MainPetSlot>.displayAllArrows() {
    forEach { it.isArrowVisible = true }
}

/**
 * This function makes all empty slot arrows matching the predicate visible
 * */
fun List<MainPetSlot>.displayEmptySlotArrowsIf(predicate : () -> Boolean) {
    forEach { if (it.isEmpty && predicate()) it.isArrowVisible = true }
}