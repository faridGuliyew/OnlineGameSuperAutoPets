package com.example.myfirstonlinegame.ui.screens.game_screen.assets

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.screens.game_screen.GameItem
import com.example.myfirstonlinegame.ui.screens.game_screen.Pet
import com.example.myfirstonlinegame.ui.screens.game_screen.StatType
import com.example.myfirstonlinegame.ui.theme.MainTextStyle

@Composable
fun GameAsset(
    size: DpSize = DpSize(48.dp, 48.dp),
    item: GameItem,
    translationY: Dp,
    translationX: Dp,
    onSelected: (GameItem) -> Unit,
) {
    Image(
        modifier = Modifier
            .size(size)
            .graphicsLayer {
                this.translationY = translationY.toPx()
                this.translationX = translationX.toPx()
            }
            .clickable {
                onSelected(item)
            },
        painter = painterResource(id = R.drawable.cricket),
        contentDescription = "item"
    )
}

@Composable
fun PetAsset(
    item: Pet,
    translationY: Dp,
    translationX: Dp,
    onSelected: (Pet) -> Unit,
) {
    val textMeasurer = rememberTextMeasurer()
    Image(
        modifier = Modifier
            .size(48.dp)
            .graphicsLayer {
                this.translationY = translationY.toPx()
                this.translationX = translationX.toPx()
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onSelected(item)
            },
        painter = painterResource(id = item.image),
        contentDescription = "pet"
    )

    PetStatAsset(
        type = StatType.ATTACK,
        value = item.attack,
        translationX = translationX,
        translationY = translationY + 40.dp,
        textMeasurer = textMeasurer
    )
    PetStatAsset(
        type = StatType.HEALTH,
        value = item.health,
        translationX = translationX + 30.dp,
        translationY = translationY + 40.dp,
        textMeasurer = textMeasurer
    )
    if (item.isShopPet.not()) {
        LevelAsset(
            level = item.level,
            translationX = translationX,
            translationY = translationY - 32.dp
        )
    } else {
        /*
        * RoundAsset()
        * */
    }
}

@Composable
fun SlotAsset(
    translationX: Dp,
    translationY: Dp,
    onClick: () -> Unit = {},
) {
    Image(
        modifier = Modifier
            .size(width = 56.dp, height = 36.dp)
            .graphicsLayer {
                this.translationY = translationY.toPx()
                this.translationX = translationX.toPx()
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            },
        painter = painterResource(id = R.drawable.ic_ground),
        contentDescription = "ground"
    )
}

@Composable
fun MainSlotAsset(
    isArrowActive: Boolean,
    translationX: Dp,
    translationY: Dp,
    onClick: () -> Unit,
) {
    SlotAsset(translationX, translationY, onClick)
    if (isArrowActive) {
        val animatedTranslation by rememberInfiniteTransition(label = "").animateFloat(
            initialValue = 100f,
            targetValue = 60f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1000),
                repeatMode = RepeatMode.Reverse
            ),
            label = ""
        )
        Image(
            modifier = Modifier
                .size(36.dp)
                .graphicsLayer {
                    this.translationY = translationY.toPx() - animatedTranslation.dp.toPx()
                    this.translationX = translationX.toPx() + 10.dp.toPx()
                },
            painter = painterResource(id = R.drawable.ic_selection),
            contentDescription = "arrow"
        )
    }
}

@Composable
fun PetStatAsset(
    type: StatType,
    value: Int,
    translationX: Dp,
    translationY: Dp,
    textMeasurer: TextMeasurer,
    size : Int = 24,
    textStyle: TextStyle = TextStyle.MainTextStyle.copy(fontSize = (size / 2).sp),
) {
    Image(modifier = Modifier
        .size(width = size.dp, height = size.dp)
        .graphicsLayer {
            this.translationY = translationY.toPx()
            this.translationX = translationX.toPx()
        }
        .drawWithCache {
            val textSize =
                textMeasurer.measure(value.toString(), style = textStyle).size
            onDrawWithContent {
                drawContent()
                drawText(
                    textMeasurer = textMeasurer,
                    text = value.toString(),
                    topLeft = Offset(
                        center.x - textSize.width / 2,
                        center.y - textSize.height / 2
                    ),
                    style = textStyle
                )
            }
        },
        painter = painterResource(id = type.image),
        contentDescription = "stat"
    )
}

@Composable
fun LevelAsset(
    level: Int,
    translationX: Dp,
    translationY: Dp,
) {
    Image(
        modifier = Modifier
            .size(48.dp)
            .graphicsLayer {
                this.translationY = translationY.toPx()
                this.translationX = translationX.toPx()
            },
        painter = painterResource(
            id = when (level) {
                1 -> R.drawable.level_1
                2 -> R.drawable.level_2
                3 -> R.drawable.level_3
                else -> R.drawable.level_1
            }
        ),
        contentDescription = "lvl"
    )
}

@Composable
fun GameScreenBackground() {
    Image(
        modifier = Modifier.fillMaxHeight(),
        painter = painterResource(id = R.drawable.bg_game),
        contentDescription = "bg",
        contentScale = ContentScale.FillHeight,
    )
}


@Preview
@Composable
private fun PetAssetPrev() {
    Box(modifier = Modifier.fillMaxSize()) {
        PetAsset(
            item = Pet(title = "cricket", "af", R.drawable.cricket, 1, 2,  3,1),
            translationY = 0.dp,
            translationX = 0.dp
        ) {

        }
    }
}