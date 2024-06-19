package com.example.myfirstonlinegame.ui.components.shared.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.ui.theme.LapsusPro

@Composable
fun TextPrimary(
    modifier: Modifier = Modifier,
    text : String,
    textAlign: TextAlign = TextAlign.Start,
    fontSize : TextUnit,
    color: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = text,
        letterSpacing = 1.5.sp,
        fontSize = fontSize,
        fontFamily = FontFamily.LapsusPro,
        color = color,
        textAlign = textAlign
    )
}