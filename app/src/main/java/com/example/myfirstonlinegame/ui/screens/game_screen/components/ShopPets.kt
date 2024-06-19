package com.example.myfirstonlinegame.ui.screens.game_screen.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myfirstonlinegame.ui.screens.game_screen.Pet
import com.example.myfirstonlinegame.ui.screens.game_screen.assets.PetAsset

@Composable
fun ShopPets(
    shopPets: List<Pet>,
    onPetSelected: (Pet) -> Unit,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        shopPets.forEach { pet ->
            PetAsset(
                item = pet,
                translationY = this@BoxWithConstraints.maxHeight * 0.72f - 24.dp,
                translationX = this@BoxWithConstraints.maxWidth * 0.2f + (48.dp + 11.dp) * pet.position
            ) {
                onPetSelected(it)
            }
        }
    }
}