package com.example.androidpokedexcompose.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Handler
import android.os.Looper
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidpokedexcompose.ui.screens.SplashScreen
import com.example.androidpokedexcompose.ui.theme.AndroidPokedexComposeTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    companion object {
        private const val TIME_SPLASH: Long =  5 * 1000
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

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    AndroidPokedexComposeTheme {
        SplashScreen()
    }
}