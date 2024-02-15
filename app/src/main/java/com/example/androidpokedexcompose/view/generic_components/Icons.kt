package com.example.androidpokedexcompose.view.generic_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Close
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
fun BackIcon(color: Color, onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Outlined.Close, contentDescription = "Back",
        modifier = Modifier.width(40.dp).height(40.dp).clickable { onClick.invoke() },
        tint = color
    )
}