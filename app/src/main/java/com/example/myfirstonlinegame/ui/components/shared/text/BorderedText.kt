package com.example.myfirstonlinegame.ui.components.shared.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BorderedText(text: String) {
    Text(
        modifier = Modifier
            .background(color = Color.Black, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 1.4.dp)
            .padding(top = 1.4.dp)
            .padding(bottom = 2.dp)
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .padding(3.dp),
        text = text
    )
}