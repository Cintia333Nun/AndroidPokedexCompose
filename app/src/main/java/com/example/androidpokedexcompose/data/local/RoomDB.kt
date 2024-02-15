package com.example.androidpokedexcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EntityPokemon::class],
    version = DB.VERSION,
    exportSchema = false,
)
abstract class RoomDB: RoomDatabase() {
    abstract fun daoPokemons(): DaoPokemons
}