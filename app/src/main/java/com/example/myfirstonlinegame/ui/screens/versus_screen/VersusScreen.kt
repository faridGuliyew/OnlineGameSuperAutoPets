package com.example.myfirstonlinegame.ui.screens.versus_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.components.core.Screen
import com.example.myfirstonlinegame.ui.components.modifiers.heightInFraction
import com.example.myfirstonlinegame.ui.components.modifiers.widthInFraction
import com.example.myfirstonlinegame.ui.components.shared.button.PlayerPackButton
import com.example.myfirstonlinegame.ui.components.shared.button.WoodenBigButton
import com.example.myfirstonlinegame.ui.components.shared.image.SuperAutoPetsLogo

@Composable
fun VersusScreen(
    onAction : (VersusScreenActions) -> Unit
) {
    Screen(
        backgroundImage = R.drawable.bg_home,
        backgroundBlur = 5.dp,
        topLeft = {
            SuperAutoPetsLogo()
        },
        bottomRight = {
            WoodenBigButton(
                text = "Start",
                fontSize = 36.sp,
                horizontalPadding = 8.dp,
                minWidth = null
            ) {
                onAction(VersusScreenActions.OnPlay)
            }
        }
    ) {
        VersusScreenContent(onAction)
    }
}

@Composable
fun VersusScreenContent(
    onAction : (VersusScreenActions) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.heightInFraction(0.25f))
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.widthInFraction(1/3f))
            }
            items(10) {
                PlayerPackButton()
            }
            item {
                Spacer(modifier = Modifier.widthInFraction(1/3f))
            }
        }
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360, showBackground = true)
@Composable
private fun VersusScreenPrev() {
    VersusScreen {

    }
}