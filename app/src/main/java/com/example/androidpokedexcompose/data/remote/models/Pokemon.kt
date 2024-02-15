package com.example.androidpokedexcompose.data.remote.models

import com.google.gson.annotations.SerializedName

data class ResponseListPokemons(
    @SerializedName("results") val results: List<Pokemon>,
)

data class Pokemon(
    @SerializedName("name") val name: String = "",
    @SerializedName("url") val url: String = "",
    var finalImage: String? = "",
    var isFavorite: Boolean = false
)