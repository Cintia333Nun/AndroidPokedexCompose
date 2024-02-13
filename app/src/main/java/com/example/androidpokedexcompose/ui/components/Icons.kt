package com.example.androidpokedexcompose.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StarIcon(color: Color) {
    Icon(
        imageVector = Icons.Filled.Star, contentDescription = "Star",
        modifier = Modifier.width(24.dp).height(24.dp),
        tint = color
    )
}

@Composable
fun BackIcon() {
    Icon(
        imageVector = Icons.Filled.ArrowBack, contentDescription = "Back",
        modifier = Modifier.width(24.dp).height(24.dp)
    )
}