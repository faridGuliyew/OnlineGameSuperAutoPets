package com.example.myfirstonlinegame.ui.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.myfirstonlinegame.ui.components.shared.text.BorderedText

@Composable
fun HomeScreen(onAction : (HomeScreenActions) -> Unit) {
    Screen(
        backgroundImage = R.drawable.bg_home,
        backgroundBlur = 5.dp,
        topLeft = {
            SuperAutoPetsLogo()
        }, bottomRight = {
            BorderedText(text = "You look great today!")
        }
    ) {
        HomeScreenContent(onAction)
    }
}

@Composable
fun HomeScreenContent(
    onAction : (HomeScreenActions) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        WoodenBigButton("Play") {
            onAction(HomeScreenActions.OnPlay)
        }
        WoodenButton("Pets") {}
        WoodenButton("Customize") {}
        WoodenButton("History") {}
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360, showBackground = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen {

    }
}