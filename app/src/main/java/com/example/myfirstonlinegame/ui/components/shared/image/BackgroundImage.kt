package com.example.myfirstonlinegame.ui.components.shared.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun BackgroundImage(
    @DrawableRes backgroundImage: Int,
    backgroundImageAlignment: Alignment,
    backgroundBlur: Dp,
) {
    Image(
        modifier = Modifier.fillMaxWidth()
            .blur(radius = backgroundBlur),
        painter = painterResource(id = backgroundImage),
        contentDescription = "bg",
        contentScale = ContentScale.FillWidth,
        alignment = backgroundImageAlignment
    )
}