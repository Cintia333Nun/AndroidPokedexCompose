package com.example.androidpokedexcompose.view.generic_components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.example.androidpokedexcompose.theme.colorPrimary

@Composable
fun SemiCircle(color: Color = colorPrimary) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val arcHeight = 800F
        val offsetY = - (arcHeight / 2)
        drawArc(
            color,
            0f,
            180f,
            useCenter = true,
            size = Size(canvasWidth, arcHeight),
            topLeft = Offset(x = 0F, y = offsetY)
        )
    }
}