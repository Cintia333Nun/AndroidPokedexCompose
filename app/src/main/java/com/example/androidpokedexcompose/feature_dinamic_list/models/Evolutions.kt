package com.example.androidpokedexcompose.feature_dinamic_list.models

import com.google.gson.annotations.SerializedName

data class Evolutions (
    @SerializedName("chain") val chain: Chain,
    @SerializedName("evolves_to") val evolvesTo: List<Chain>?
)

data class Chain(
    @SerializedName("species") val species: Species,
    @SerializedName("evolves_to") val evolvesTo: List<Chain>?
)

data class Species(
    @SerializedName("name") val name: String
)