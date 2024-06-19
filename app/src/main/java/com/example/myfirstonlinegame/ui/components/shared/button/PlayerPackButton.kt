package com.example.myfirstonlinegame.ui.components.shared.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.theme.Colors
import com.example.myfirstonlinegame.ui.theme.Colors.Orange
import com.example.myfirstonlinegame.ui.theme.LapsusPro

@Composable
@Preview
fun PlayerPackButton(
    @DrawableRes icon : Int = R.drawable.pet_turtle,
    text: String = "Turtle Pack",
    onClick: () -> Unit = {},
) {
    BoxWithConstraints {
        Box (
            modifier = Modifier.width(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        width = 3.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        color = Colors.Wood,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(12.dp)
                    .background(
                        color = Colors.Cream,
                        shape = RoundedCornerShape(6.dp)
                    )
            ) {
                Image(
                    modifier = Modifier
                        .size(96.dp)
                        .padding(horizontal = 18.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "pet"
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .graphicsLayer {
                        translationY = -(this@BoxWithConstraints.maxHeight / 20).toPx()
                    }
                    .background(
                        color = Orange
                    )
                    .padding(vertical = 6.dp)
                ,
                text = text,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.LapsusPro,
                letterSpacing = 1.sp
            )
        }
    }
}