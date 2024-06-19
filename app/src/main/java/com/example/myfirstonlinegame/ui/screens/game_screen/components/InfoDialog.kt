package com.example.myfirstonlinegame.ui.screens.game_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.ui.screens.game_screen.GameItem
import com.example.myfirstonlinegame.ui.screens.game_screen.Pet
import com.example.myfirstonlinegame.ui.screens.game_screen.StatType
import com.example.myfirstonlinegame.ui.screens.game_screen.assets.PetStatAsset
import com.example.myfirstonlinegame.ui.screens.game_screen.core.PetDB
import com.example.myfirstonlinegame.ui.theme.Colors
import com.example.myfirstonlinegame.ui.theme.ComicSans
import com.example.myfirstonlinegame.ui.theme.LapsusPro
import com.example.myfirstonlinegame.ui.theme.MainTextStyle

@Composable
fun InfoDialog(selectedItem: GameItem?) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = selectedItem != null,
            enter = scaleIn(animationSpec = tween(300)),
            exit = scaleOut(animationSpec = tween(300))
        ) {
            InfoDialogContent(selectedItem = selectedItem)

        }
    }
}

@Composable
fun BoxWithConstraintsScope.InfoDialogContent(
    selectedItem: GameItem?
) {
    if (selectedItem == null) return
    
    Box(modifier = Modifier
        .width(LocalConfiguration.current.screenWidthDp.dp * 0.30f)
        .graphicsLayer {
            translationY = (this@InfoDialogContent.maxHeight * 0.3f).toPx()
            translationX = (this@InfoDialogContent.maxWidth * 0.65f).toPx()
        }
        .background(
            color = Color.Black, shape = RoundedCornerShape(4.dp)
        )
        .padding(horizontal = 2.dp)
        .padding(top = 2.dp)
        .padding(bottom = 4.dp)
        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
        .padding(6.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                    drawLine(
                        brush = Brush.linearGradient(
                            0f to Color.Transparent,
                            0.5f to Color.Black,
                            1f to Color.Transparent
                        ),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                    )
                },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = selectedItem.image),
                    contentDescription = "item"
                )
                Text(
                    text = selectedItem.title.uppercase(),
                    style = TextStyle.MainTextStyle,
                    color = Colors.Orange,
                    fontSize = 20.sp
                )
                PetStatAsset(
                    type = StatType.COIN,
                    value = selectedItem.cost,
                    translationX = 0.dp,
                    translationY = 0.dp,
                    textMeasurer = rememberTextMeasurer(),
                    textStyle = TextStyle.MainTextStyle.copy(
                        color = Color.Black
                    ),
                    size = 32
                )
            }
            Text(
                text = selectedItem.info,
                style = TextStyle(fontFamily =  FontFamily.ComicSans, fontWeight = FontWeight.Normal),
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun InfoDialogPrev() {
    InfoDialog(selectedItem = PetDB.getRandomPets(1).first())
}