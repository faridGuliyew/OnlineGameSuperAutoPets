package com.example.myfirstonlinegame.ui.screens.game_screen.assets

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastAll
import com.example.myfirstonlinegame.ui.screens.game_screen.GameItem
import com.example.myfirstonlinegame.ui.screens.game_screen.MainPetSlot
import com.example.myfirstonlinegame.ui.screens.game_screen.Pet

@Composable
fun ShopSlots() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        repeat(3) {
            SlotAsset(
                translationY = this@BoxWithConstraints.maxHeight * 0.72f,
                translationX = this@BoxWithConstraints.maxWidth * 0.2f + (56.dp + 3.dp) * it
            )
        }
    }
}

@Composable
fun MainPetSlots(
    slots : List<MainPetSlot>,
    onSlotSelected: (index: Int) -> Unit,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        slots.forEachIndexed { index, mainPetSlot ->
            MainSlotAsset(
                isArrowActive = mainPetSlot.isArrowVisible,
                translationY = this@BoxWithConstraints.maxHeight * 0.46f,
                translationX = this@BoxWithConstraints.maxWidth * 0.2f + (56.dp + 3.dp) * index,
                onClick = { onSlotSelected(index) }
            )
        }
    }
}