package com.example.myfirstonlinegame.ui.components.modifiers

import android.annotation.SuppressLint
import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.heightInFraction(@FloatRange(0.0,1.0) fraction : Float) = this.then(
    Modifier.height(LocalConfiguration.current.screenHeightDp.dp * fraction)
)

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.widthInFraction(@FloatRange(0.0,1.0) fraction : Float) = this.then(
    Modifier.width(LocalConfiguration.current.screenWidthDp.dp * fraction)
)