package com.example.androidpokedexcompose.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = DB.TABLE_POKEMON, primaryKeys = [DB.COLUMN_POKEMON_NAME])
data class EntityPokemon(
    @ColumnInfo(name = DB.COLUMN_POKEMON_NAME) val name: String,
    @ColumnInfo(name = DB.COLUMN_POKEMON_URL) val url: String,
    @ColumnInfo(name = DB.COLUMN_POKEMON_IMAGE_URL) val urlImage: String,
    @ColumnInfo(name = DB.COLUMN_POKEMON_IS_FAV) val isFav: Boolean,
)