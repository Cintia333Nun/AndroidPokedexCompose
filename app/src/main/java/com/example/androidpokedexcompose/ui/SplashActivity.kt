package com.example.androidpokedexcompose.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Handler
import android.os.Looper
import android.content.Intent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.ui.theme.AndroidPokedexComposeTheme
import com.example.androidpokedexcompose.ui.theme.colorPrimary

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    companion object {
        const val TIME_SPLASH: Long =  5 * 1000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPokedexComposeTheme {
                SplashScreen()
            }
        }
        startPokedexActivityTime()
    }

    private fun startPokedexActivityTime() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToPokedexActivity()
        }, TIME_SPLASH)
    }

    private fun goToPokedexActivity() {
        val options = android.app.ActivityOptions.makeCustomAnimation(
            this, android.R.anim.fade_in, android.R.anim.fade_out
        )
        val intent = Intent(this, PokedexActivity::class.java)
        startActivity(intent, options.toBundle())
    }
}

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

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}