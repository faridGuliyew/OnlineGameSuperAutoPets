package com.example.myfirstonlinegame.ui.screens.game_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.components.shared.container.OutlinedContainer
import com.example.myfirstonlinegame.ui.theme.MainTextStyle

@Composable
fun GameStat(
    @DrawableRes icon : Int,
    value : String
) {
    Box (
        contentAlignment = Alignment.CenterStart
    ) {
        OutlinedContainer (
            modifier = Modifier.graphicsLayer {
                translationX = 16.dp.toPx()
            }
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = value,
                style = TextStyle.MainTextStyle,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = icon),
            contentDescription = "Star"
        )
    }

}