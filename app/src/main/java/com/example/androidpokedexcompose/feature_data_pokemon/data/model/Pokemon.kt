package com.example.androidpokedexcompose.feature_data_pokemon.data.model

import com.google.gson.annotations.SerializedName

data class ResponseListPokemons(
    @SerializedName("results") val results: List<Pokemon>,
)

data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    var isFavorite: Boolean
)