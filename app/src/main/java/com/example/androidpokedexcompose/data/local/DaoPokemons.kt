package com.example.androidpokedexcompose.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query

@Dao
interface DaoPokemons {
    @Query("SELECT * FROM ${DB.TABLE_POKEMON} ORDER BY ${DB.COLUMN_POKEMON_NAME} DESC")
    fun getPagingPokemons(): PagingSource<Int, EntityPokemon>
}