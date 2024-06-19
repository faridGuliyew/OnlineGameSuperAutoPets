package com.example.myfirstonlinegame.ui.screens.play_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.components.core.Screen
import com.example.myfirstonlinegame.ui.components.shared.button.WoodenBigButton
import com.example.myfirstonlinegame.ui.components.shared.button.WoodenButton
import com.example.myfirstonlinegame.ui.components.shared.image.SuperAutoPetsLogo

@Composable
fun PlayScreen(
    onAction: (PlayScreenActions) -> Unit
) {
    Screen(
        backgroundImage = R.drawable.bg_home,
        backgroundBlur = 5.dp,
        topLeft = {
            SuperAutoPetsLogo()
        }
    ) {
        PlayScreenContent(onAction)
    }
}

@Composable
fun PlayScreenContent(
    onAction: (PlayScreenActions) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WoodenBigButton("Arena") {}
        Spacer(modifier = Modifier.height(12.dp))
        WoodenButton(text = "Versus") {
            onAction(PlayScreenActions.OnVersus)
        }
        Spacer(modifier = Modifier.height(12.dp))
        WoodenButton("With friends") {}
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360, showBackground = true)
@Composable
private fun PlayScreenPrev() {
    PlayScreen {

    }
}