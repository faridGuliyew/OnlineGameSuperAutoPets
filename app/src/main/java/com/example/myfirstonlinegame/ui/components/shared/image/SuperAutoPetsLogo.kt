package com.example.myfirstonlinegame.ui.components.shared.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myfirstonlinegame.R

@Composable
fun SuperAutoPetsLogo() {
    Image(
        modifier = Modifier
            .padding(start = 8.dp)
            .graphicsLayer { translationY = -16.dp.toPx() }
            .size(96.dp),
        painter = painterResource(id = R.drawable.logo_super_auto_pets),
        contentDescription = "logo"
    )
}