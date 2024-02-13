package com.example.androidpokedexcompose.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.example.androidpokedexcompose.ui.theme.colorPrimary

@Composable
fun SemiCircle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val arcHeight = 800F
        val offsetY = - (arcHeight / 2)
        drawArc(
            colorPrimary,
            0f,
            180f,
            useCenter = true,
            size = Size(canvasWidth, arcHeight),
            topLeft = Offset(x = 0F, y = offsetY)
        )
    }
}