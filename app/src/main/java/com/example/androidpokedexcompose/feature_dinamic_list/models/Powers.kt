package com.example.androidpokedexcompose.feature_dinamic_list.models

import com.google.gson.annotations.SerializedName

data class Powers (
    @SerializedName("abilities") val abilities: List<Abilities>,
)

data class Abilities (
    @SerializedName("ability") val ability: Ability,
)

data class Ability (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)