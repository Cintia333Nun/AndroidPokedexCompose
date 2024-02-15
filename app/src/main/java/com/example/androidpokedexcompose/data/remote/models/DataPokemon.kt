package com.example.androidpokedexcompose.data.remote.models

import com.google.gson.annotations.SerializedName

data class DataPokemon(
    @SerializedName("base_happiness") val baseHappiness: Int = 0,
    @SerializedName("capture_rate") val captureRate: Int = 0,
    @SerializedName("evolution_chain") val evolutionChain: EvolutionChain = EvolutionChain(),
    @SerializedName("egg_groups") val eggGroups: List<Egg> = listOf(),
)

data class EvolutionChain(
    val url: String = ""
)
data class Egg(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)
