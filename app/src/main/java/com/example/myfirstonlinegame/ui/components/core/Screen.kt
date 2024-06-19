package com.example.myfirstonlinegame.ui.components.core

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.myfirstonlinegame.ui.components.shared.image.BackgroundImage

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundImage: Int? = null,
    backgroundImageAlignment: Alignment = Alignment.TopCenter,
    backgroundBlur: Dp = 0.dp,
    topLeft: @Composable () -> Unit = {},
    topRight: @Composable () -> Unit = {},
    bottomLeft: @Composable () -> Unit = {},
    bottomRight: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    //Breaks preview
    HideStatusBar()
    DisableSwipeToGoBackGesture()
    if (backgroundImage != null) BackgroundImage(backgroundImage,backgroundImageAlignment, backgroundBlur)
    Box(modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(6.dp)
        ) {
            topLeft()
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
        ) {
            topRight()
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(6.dp)
        ) {
            bottomLeft()
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(6.dp)
        ) {
            bottomRight()
        }
        content()
    }
}

@Composable
fun HideStatusBar() {
    val activity = LocalContext.current as ComponentActivity
    val window = activity.window
    val insetsController = WindowCompat.getInsetsController(window, window.decorView)
    insetsController.apply {
        hide(WindowInsetsCompat.Type.systemBars())
        hide(WindowInsetsCompat.Type.statusBars())
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
    // Set the status bar to be transparent if needed
    window.statusBarColor = Color.Transparent.toArgb()
    window.navigationBarColor = Color.Transparent.toArgb()
}

@Composable
fun LoadingDialog(isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.5f))
                .padding(12.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.TopStart),
                color = Color.White
            )
        }
    }
}

    @Composable
    fun DisableSwipeToGoBackGesture() {
        BackHandler {
            //do nothing
        }
    }