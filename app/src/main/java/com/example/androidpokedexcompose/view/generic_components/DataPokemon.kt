package com.example.androidpokedexcompose.view.generic_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidpokedexcompose.theme.colorCard
import com.example.androidpokedexcompose.theme.secondary

@Composable
fun DataPokemon(title: String, description: String) {
    Box(
        modifier = Modifier
            .background(
                colorCard, shape = RoundedCornerShape(8.dp)
            )
            .padding(all = 10.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = title, color = secondary,
                fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
            )
            Text(text = description)
        }
    }
}