package com.example.androidpokedexcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.ui.theme.colorPrimary

@Composable
fun SplashScreen() {
    Surface(color = colorPrimary) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.animation_progress)
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
            Image(
                painter = painterResource(id = R.drawable.pokemon),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
                    .width(200.dp)
                    .height(200.dp)
            )
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.align(Alignment.BottomCenter)
                    .width(150.dp)
                    .height(150.dp)
            )
        }
    }
}