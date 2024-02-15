package com.example.androidpokedexcompose.feature_pokedex.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidpokedexcompose.data.remote.models.Pokemon
import com.example.androidpokedexcompose.view.view_model.PokedexViewModel

@Composable
fun ListOfPokemons(viewModel: PokedexViewModel, data: List<Pokemon>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 10.dp)
    ) {
        items(data) {item ->
            CardPokemon(viewModel, item, navController)
        }
    }
}