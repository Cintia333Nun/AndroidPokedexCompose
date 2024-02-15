package com.example.androidpokedexcompose.data.utils

object RoutesUtils {
    const val POKEDEX_TO_GENERIC_DATA = "${DestinationsUtils.POKEMON_DATA}/" +
            "{${ParamsPokemonData.POKEDEX_NAME}}/" +
            "?url={${ParamsPokemonData.POKEDEX_URL}}"
    const val GENERIC_DATA_TO_POKEMON_DATA = "${DestinationsUtils.POKEMON_GENERAL}/" +
            "{${ParamsPokemonData.POKEDEX_NAME}}/" +
            "{${ParamsPokemonData.POKEDEX_DATA_TYPE}}/" +
            "?url={${ParamsPokemonData.POKEDEX_URL}}"
}

object DestinationsUtils {
    const val POKEDEX = "POKEDEX"
    const val POKEMON_DATA = "POKEMON_DATA"
    const val POKEMON_GENERAL = "POKEMON_GENERAL"
}

object ParamsPokemonData {
    const val POKEDEX_NAME = "POKEDEX_NAME"
    const val POKEDEX_URL = "POKEDEX_URL"
    const val POKEDEX_DATA_TYPE = "POKEDEX_DATA_TYPE"
    const val POKEDEX_TYPE_POWERS = "POKEDEX_TYPE_POWERS"
    const val POKEDEX_TYPE_EVOLUTIONS = "POKEDEX_TYPE_EVOLUTIONS"
}