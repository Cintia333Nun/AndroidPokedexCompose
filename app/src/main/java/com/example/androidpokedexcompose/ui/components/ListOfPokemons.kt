package com.example.androidpokedexcompose.ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidpokedexcompose.data.model.Pokemon

@Composable
fun ListOfPokemons(data: List<Pokemon>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 50.dp, start = 40.dp, end = 40.dp, bottom = 10.dp)
    ) {
        items(data) { item ->
            CardPokemon(item)
        }
    }
}