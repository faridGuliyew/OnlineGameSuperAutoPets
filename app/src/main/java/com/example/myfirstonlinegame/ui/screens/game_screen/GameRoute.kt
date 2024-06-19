package com.example.myfirstonlinegame.ui.screens.game_screen

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.screens.game_screen.core.PetDB
import com.example.myfirstonlinegame.ui.screens.game_screen.extensions.displayEmptySlotArrows
import com.example.myfirstonlinegame.ui.screens.game_screen.extensions.displayEmptySlotArrowsIf
import com.example.myfirstonlinegame.ui.screens.game_screen.extensions.moveToSlot
import com.example.myfirstonlinegame.ui.screens.game_screen.extensions.hideAllArrows
import com.example.myfirstonlinegame.ui.screens.game_screen.extensions.sell
import kotlinx.serialization.Serializable

@Serializable
data object GameRoute

sealed interface GameActions {
    data object Roll : GameActions
    data object EndTurn : GameActions
    data class OnGameItemSelected (val item: GameItem) : GameActions
    data class OnShopPetSelected(val pet: Pet) : GameActions
    data class OnMainPetSelected(val pet: Pet) : GameActions
    data class BuyPet(val pet: Pet) : GameActions
    data object UnselectAll : GameActions
    data class OnMainPetSlotSelected (val slotPosition : Int) : GameActions
    data class SellPet (val pet : Pet) : GameActions
}

@Composable
fun GameRoute() {
    //val navController = LocalNavController.current
    val context = LocalContext.current
    val ambienceMediaPlayer = remember {
        MediaPlayer.create(context, R.raw.ambience_forest).apply {
            isLooping = true
        }
    }
    val coinMediaPlayer = remember {
        MediaPlayer.create(context, R.raw.coin)
    }
    LaunchedEffect(key1 = Unit) {
        ambienceMediaPlayer.start()
    }


    //All game states
    var selectedItem by remember { mutableStateOf<GameItem?>(null) }
    var selectedPet by remember { mutableStateOf<Pet?>(null) }
    val mainPets = remember { mutableStateListOf<Pet>() }
    val shopPets = remember { mutableStateListOf(*PetDB.getRandomPets(3).toTypedArray()) }
    val mainPetSlots = remember { mutableStateListOf(*(1..5).map { MainPetSlot()}.toTypedArray()) }
    var totalHealth by remember { mutableIntStateOf(5) }
    var totalCoin by remember { mutableIntStateOf(25) }
    var currentRound by remember { mutableIntStateOf(1) }

    fun unselectAll() {
        selectedItem = null
        selectedPet = null
        mainPetSlots.hideAllArrows()
    }

    fun notEnoughCoin() {

    }
    fun buyPet(selectedPet: Pet, shopPrice : Int) {
        coinMediaPlayer.start()
        if (totalCoin < shopPrice) return notEnoughCoin()
        shopPets.remove(selectedPet)
        mainPets.add(selectedPet.apply { isShopPet = false })
        totalCoin -= shopPrice
        unselectAll()
    }

    fun sellPet(pet: Pet) {
        coinMediaPlayer.start()
        totalCoin += pet.level
        mainPets.sell(pet, mainPetSlots)
        unselectAll()
    }

    LaunchedEffect(key1 = mainPets.size) {
        Log.i("MainPets", "PETS: ${mainPets.map { "${it.title} - ${it.position}" }}")
    }

    GameScreen(
        mainPets = mainPets,
        shopPets = shopPets,
        selectedItem = selectedItem,
        selectedPet = selectedPet,
        mainPetSlots = mainPetSlots,
        totalHealth = totalHealth,
        totalCoin = totalCoin,
        currentRound = currentRound
    ) {
        run {
            when (it) {
                is GameActions.BuyPet -> TODO()
                GameActions.EndTurn -> TODO()
                is GameActions.OnMainPetSlotSelected -> {
                    val slot = mainPetSlots[it.slotPosition]
                    if (slot.isArrowVisible.not() || selectedItem == null) return@run

                    selectedPet.moveToSlot(it.slotPosition, mainPetSlots)
                    if (selectedPet!!.isShopPet) buyPet(selectedPet = selectedPet!!, shopPrice = 3)
                    unselectAll()
                }
                GameActions.Roll -> {
                    if (totalCoin == 0) return@run notEnoughCoin()
                    val randomShopPets = PetDB.getRandomPets(3)
                    shopPets.clear()
                    shopPets.addAll(randomShopPets)
                    coinMediaPlayer.start()
                    totalCoin--
                }
                is GameActions.OnShopPetSelected -> {
                    mainPetSlots.hideAllArrows()
                    selectedPet = it.pet
                    mainPetSlots.displayEmptySlotArrowsIf {
                        totalCoin >= 3
                    }
                }
                GameActions.UnselectAll -> unselectAll()
                is GameActions.OnGameItemSelected -> {
                    selectedItem = it.item
                }
                is GameActions.OnMainPetSelected -> {
                    selectedPet = it.pet
                    mainPetSlots.displayEmptySlotArrows()
                }

                is GameActions.SellPet -> {
                    sellPet(it.pet)
                }
            }
        }
    }
}

@Preview
@Composable
private fun GamePreview() {
    GameRoute()
}