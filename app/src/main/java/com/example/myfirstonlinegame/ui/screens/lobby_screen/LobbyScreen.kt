package com.example.myfirstonlinegame.ui.screens.lobby_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.domain.services.Lobby
import com.example.myfirstonlinegame.domain.services.Player
import com.example.myfirstonlinegame.ui.components.core.Screen
import com.example.myfirstonlinegame.ui.components.shared.button.WoodenButton
import com.example.myfirstonlinegame.ui.components.shared.container.OutlinedContainer
import com.example.myfirstonlinegame.ui.components.shared.text.TextPrimary
import com.example.myfirstonlinegame.ui.theme.Colors.DarkRed

@Composable
fun LobbyScreen(
    screenState: LobbyScreenState,
    onAction : (LobbyScreenActions) -> Unit
) {
    Screen(
        backgroundImage = R.drawable.bg_home,
        backgroundBlur = 5.dp,
        topLeft = {
            WoodenButton(
                text = "Leave",
                textColor = Color.Black,
                fontSize = 20.sp,
                horizontalPadding = 64.dp,
                backgroundColor = DarkRed,
                minWidth = null
            ) {
                onAction(LobbyScreenActions.OnLeave)
            }
        },
        bottomLeft = {
            WoodenButton(
                text = "Seconds:\t${screenState.lobby?.timeLeft?:30}",
                fontSize = 16.sp,
                horizontalPadding = 24.dp,
                minWidth = null
            ) {
                
            }
        }
    ) {
        screenState.lobby?.let {
            LobbyScreenContent(it)
        }
    }
}

@Composable
fun LobbyScreenContent(
    lobby: Lobby
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 6.dp),
        horizontalAlignment = Alignment.End
    ) {

        LazyColumn(
            modifier = Modifier.padding(end = 64.dp)
        ) {
            items(lobby.players) { player->
                LobbyPlayerListItem(player = player)
            }
        }
    }
}


@Composable
fun LobbyPlayerListItem(player: Player) {
    OutlinedContainer(
        modifier = Modifier.fillMaxWidth(0.45f)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.pet_turtle),
                contentDescription = "pet"
            )

            TextPrimary(
                modifier = Modifier.weight(1f),
                text = player.username,
                fontSize = 20.sp
            )
            TextPrimary(
                text = player.score.toString(),
                fontSize = 16.sp
            )
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.img_crown),
                contentDescription = "crown"
            )
        }
    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360, showBackground = true)
@Composable
private fun LobbyScreenPrev() {
    LobbyScreen (
        screenState = LobbyScreenState(
            lobby = null
        ), onAction = {}
    )
}