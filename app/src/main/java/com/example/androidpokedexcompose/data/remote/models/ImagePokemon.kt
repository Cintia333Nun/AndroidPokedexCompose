package com.example.androidpokedexcompose.data.remote.models

import com.google.gson.annotations.SerializedName

data class ImagePokemon(
    @SerializedName("sprites") val sprites: ImagesPokemon
)

data class ImagesPokemon(
    @SerializedName("front_default") val frontDefault: String
)