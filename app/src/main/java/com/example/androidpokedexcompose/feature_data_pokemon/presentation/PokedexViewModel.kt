package com.example.androidpokedexcompose.feature_data_pokemon.presentation

import androidx.lifecycle.ViewModel
import com.example.androidpokedexcompose.feature_data_pokemon.data.model.Pokemon

class PokedexViewModel: ViewModel() {
    val selectedPokemon =  Pokemon(name = "", url = "", false)
}