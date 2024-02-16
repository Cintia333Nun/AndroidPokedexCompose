package com.example.androidpokedexcompose.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidpokedexcompose.data.local.DB.Companion.COLUMN_POKEMON_IMAGE_URL
import com.example.androidpokedexcompose.data.local.DB.Companion.COLUMN_POKEMON_IS_FAV

@Dao
interface DaoPokemons {
    @Query("SELECT * FROM ${DB.TABLE_POKEMON} ORDER BY ${DB.COLUMN_POKEMON_NAME} DESC")
    fun getPagingPokemons(): PagingSource<Int, EntityPokemon>

    @Query("SELECT * FROM ${DB.TABLE_POKEMON} ORDER BY ${DB.COLUMN_POKEMON_NAME} DESC")
    fun getAllPokemons(): List<EntityPokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemons(pokemons: List<EntityPokemon>)

    @Query("SELECT $COLUMN_POKEMON_IS_FAV FROM ${DB.TABLE_POKEMON} " +
            "WHERE ${DB.COLUMN_POKEMON_NAME} = :name")
    fun getIsFavPokemon(name: String): Boolean

    @Query("UPDATE ${DB.TABLE_POKEMON} SET $COLUMN_POKEMON_IS_FAV = :isFav " +
            "WHERE ${DB.COLUMN_POKEMON_NAME} =:name")
    fun updateFavPokemon(name: String, isFav: Boolean)

    @Query("SELECT $COLUMN_POKEMON_IMAGE_URL FROM ${DB.TABLE_POKEMON} " +
            "WHERE ${DB.COLUMN_POKEMON_NAME} = :name")
    fun getUrlImagePokemon(name: String): String

    @Query("SELECT COUNT(*) FROM TABLE_POKEMON")
    fun getPokemonCount(): Int

    @Query("DELETE FROM ${DB.TABLE_POKEMON}")
    fun deletePokemons()
}