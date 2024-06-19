package com.example.myfirstonlinegame.ui.components.shared.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.ui.theme.Colors
import com.example.myfirstonlinegame.ui.theme.Colors.DarkBrown
import com.example.myfirstonlinegame.ui.theme.LapsusPro

@Composable
fun WoodenButton(
    text: String,
    textColor: Color = DarkBrown,
    fontSize : TextUnit = 32.sp,
    minWidth : Dp? = LocalConfiguration.current.screenWidthDp.dp / 3f,
    horizontalPadding : Dp = 40.dp,
    backgroundColor : Color = Colors.Wood,
    onClick: () -> Unit,
) {
    Text(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .widthIn(min = minWidth?:0.dp)
            .border(
                width = 3.dp,
                color = Color.Black,
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 12.dp)
            .padding(horizontal = horizontalPadding),
        text = text,
        letterSpacing = 1.5.sp,
        fontSize = fontSize,
        fontFamily = FontFamily.LapsusPro,
        color = textColor,
        textAlign = TextAlign.Center
    )

}
