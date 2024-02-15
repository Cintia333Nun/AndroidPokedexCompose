package com.example.androidpokedexcompose.data.local

import android.os.Build

class DB {
    companion object {
        const val NAME = "pokedex.db"
        const val VERSION = 1
        //region TABLE_POKEMON
        const val TABLE_POKEMON = "TABLE_POKEMON"
        const val COLUMN_POKEMON_NAME = "COLUMN_POKEMON_NAME"
        const val COLUMN_POKEMON_URL = "COLUMN_POKEMON_URL"
        const val COLUMN_POKEMON_IS_FAV = "COLUMN_POKEMON_IS_FAV"
        //endregion
    }
}