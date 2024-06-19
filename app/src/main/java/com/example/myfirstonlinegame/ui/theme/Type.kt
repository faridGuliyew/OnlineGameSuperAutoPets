package com.example.myfirstonlinegame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val TextStyle.Companion.MainTextStyle get() = TextStyle(
    color = Color.White,
    fontSize = 12.sp,
    fontWeight = FontWeight.ExtraBold,
    fontFamily = FontFamily.LapsusPro,
    letterSpacing = 1.5.sp
)

val FontFamily.Companion.LapsusPro get() = FontFamily(
    Font(R.font.lapsus_pro_bold)
)

val FontFamily.Companion.ComicSans get() = FontFamily(
    Font(R.font.comic_sans)
)