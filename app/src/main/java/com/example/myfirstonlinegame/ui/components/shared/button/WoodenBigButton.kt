package com.example.myfirstonlinegame.ui.components.shared.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.myfirstonlinegame.R
import com.example.myfirstonlinegame.ui.theme.Colors
import com.example.myfirstonlinegame.ui.theme.LapsusPro


@Composable
fun WoodenBigButton(
    text: String,
    fontSize : TextUnit = 56.sp,
    horizontalPadding : Dp = 0.dp,
    minWidth : Dp? = LocalConfiguration.current.screenWidthDp.dp / 3f,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .widthIn(min = minWidth ?: 0.dp)
            .border(
                width = 3.dp,
                color = Color.Black,
                shape = RoundedCornerShape(4.dp)
            )
            .background(
                color = Colors.Wood,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(12.dp)
            .background(
                color = Colors.Cream,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 8.dp)
            .padding(horizontal = horizontalPadding)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            letterSpacing = 1.5.sp,
            fontSize = fontSize,
            fontFamily = FontFamily.LapsusPro,
            color = Colors.DarkBrown
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_paw),
            tint = Colors.DarkBrown,
            contentDescription = "paw"
        )
    }
}

@Preview
@Composable
private fun WoodenBigButton() {
    WoodenBigButton(text = "Play", horizontalPadding = 12.dp, minWidth = null) {
        
    }
}