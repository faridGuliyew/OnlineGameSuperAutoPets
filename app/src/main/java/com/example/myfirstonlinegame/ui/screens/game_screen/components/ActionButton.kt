package com.example.myfirstonlinegame.ui.screens.game_screen.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.theme.Colors
import com.example.myfirstonlinegame.ui.theme.MainTextStyle

@Composable
fun ActionButton(
    text: String,
    @DrawableRes icon: Int? = null,
    onClick : () -> Unit
) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = Modifier
            .background(color = Colors.LightBrown, shape = shape)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = shape
            ).clickable {
                Log.i("ActionButton", "Clicked to $text")
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .background(color = Colors.Orange, shape = shape)
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = text,
                style = TextStyle.MainTextStyle,
                fontSize = 28.sp,
                color = Colors.DarkBrown
            )
            if (icon != null)
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "dice"
                )
        }
    }
}