package com.example.myfirstonlinegame.ui.screens.game_screen

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastAll
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.components.core.Screen
import com.example.myfirstonlinegame.ui.components.shared.image.SuperAutoPetsLogo
import com.example.myfirstonlinegame.ui.screens.game_screen.assets.GameScreenBackground
import com.example.myfirstonlinegame.ui.screens.game_screen.assets.MainPetSlots
import com.example.myfirstonlinegame.ui.screens.game_screen.assets.ShopSlots
import com.example.myfirstonlinegame.ui.screens.game_screen.components.ActionButton
import com.example.myfirstonlinegame.ui.screens.game_screen.components.GameStat
import com.example.myfirstonlinegame.ui.screens.game_screen.components.InfoDialog
import com.example.myfirstonlinegame.ui.screens.game_screen.components.MainPets
import com.example.myfirstonlinegame.ui.screens.game_screen.components.ShopPets
import kotlin.time.Duration.Companion.microseconds

/**
 * Any class implementing this interface will have an info dialog shown on the right side of the screen
 * Before showing this info, it will go through custom parser to display special characters should be used to display health and attack icons
 * */
interface GameItem {
    val title: String
    val info: String
    val image: Int
    val cost : Int
    val round : Int
}

/**
 * Represents a pet's state in the game
 * Additional things like hasPower-up and etc. will be added
 * */
class Pet(
    override val title: String,
    override val info: String,
    override val image: Int,
    override val cost: Int = 3,
    override val round: Int,
    initialHealth: Int,
    initialAttack: Int,
    initialPosition: Int = -1,
) : GameItem {
    var level by mutableIntStateOf(1)
    var health by mutableIntStateOf(initialHealth)
    var attack by mutableIntStateOf(initialAttack)
    var position by mutableIntStateOf(initialPosition)
    var isShopPet by mutableStateOf(true)

    fun copy(): Pet {
        return Pet(
            title = title,
            info = info,
            image = image,
            initialHealth = health,
            initialAttack = attack,
            round = round,
            cost = cost
        )
    }
}

/**
 * Represents a power up in the game
 * Additional things will be added to define its behaviour
 * */
class PowerUp(
    override val title: String,
    override val info: String,
    override val image: Int,
    override val cost: Int,
    override val round: Int,
    val healthIncrement: Int,
    val attackIncrement: Int,
) : GameItem

enum class StatType(val image: Int) {
    HEALTH(R.drawable.ic_health),
    ATTACK(R.drawable.ic_attack),
    COIN(R.drawable.gold)
}

/**
 * Represents a state of a main pet slot. (Shop slots have no state - yet)
 * */
class MainPetSlot {
    var isEmpty by mutableStateOf(true)
    var isArrowVisible by mutableStateOf(false)
}


@Composable
fun GameScreen(
    mainPets: List<Pet>,
    shopPets: List<Pet>,
    selectedItem: GameItem?,
    selectedPet: Pet?,
    mainPetSlots: List<MainPetSlot>,
    totalHealth: Int,
    totalCoin: Int,
    currentRound: Int,
    onGameAction: (GameActions) -> Unit,
) {

    LaunchedEffect(key1 = shopPets.size) {
        Log.i("ShopPets", "Changed to $shopPets")
    }

    GameScreenBackground()
    Screen(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onPress = {
                onGameAction(
                    GameActions.UnselectAll
                )
            })
        },
        topLeft = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                GameStat(icon = R.drawable.ic_health, value = totalHealth.toString())
                GameStat(icon = R.drawable.gold, value = totalCoin.toString())
                GameStat(icon = R.drawable.ic_round, value = currentRound.toString())
            }
        },
        bottomRight = {
            Row (
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (selectedPet?.isShopPet == false) {
                    ActionButton("Sell (${selectedPet.level})") {
                        onGameAction(GameActions.SellPet(selectedPet))
                    }
                }

                ActionButton("End turn", icon = R.drawable.sword) {}
            }
        }, bottomLeft = {
            ActionButton("Roll", icon = R.drawable.dice) {
                onGameAction(GameActions.Roll)
            }
        }
    ) {
        GameScreenContent(
            mainPets = mainPets,
            shopPets = shopPets,
            selectedItem = selectedItem,
            selectedPet = selectedPet,
            mainPetSlots = mainPetSlots,
            onGameAction
        )
    }
}


@Composable
fun GameScreenContent(
    mainPets: List<Pet>,
    shopPets: List<Pet>,
    selectedItem: GameItem?,
    selectedPet: Pet?,
    mainPetSlots: List<MainPetSlot>,
    onGameAction: (GameActions) -> Unit,
) {
    Box {
        MainPetSlots(
            slots = mainPetSlots
        ) {
            onGameAction(GameActions.OnMainPetSlotSelected(it))
        }

        MainPets(mainPets = mainPets) {
            onGameAction(GameActions.OnGameItemSelected(it))
            onGameAction(GameActions.OnMainPetSelected(it))
        }
        ShopSlots()
        ShopPets(shopPets = shopPets) {
            onGameAction(GameActions.OnGameItemSelected(it))
            onGameAction(GameActions.OnShopPetSelected(it))
        }
        InfoDialog(selectedItem)
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360, showBackground = true)
@Composable
private fun GameScreenPrev() {
    GameScreen(
        mainPets = listOf(),
        shopPets = listOf(),
        selectedItem = null,
        selectedPet = null,
        mainPetSlots = emptyList(),
        onGameAction = {}, totalHealth = 5, totalCoin = 10, currentRound = 1
    )
}