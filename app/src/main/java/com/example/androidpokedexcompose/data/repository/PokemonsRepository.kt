package com.example.androidpokedexcompose.data.repository

import com.example.androidpokedexcompose.data.local.DaoPokemons
import com.example.androidpokedexcompose.data.local.RoomDB
import com.example.androidpokedexcompose.data.remote.ApiPokemonsDataSource
import com.example.androidpokedexcompose.data.remote.ApiPokemonsInterface
import javax.inject.Inject

class PokemonsRepository @Inject constructor(private val apiInterface: ApiPokemonsInterface, private val dataBase: RoomDB) {
    fun getRemotePokemonDataSource(): ApiPokemonsDataSource = ApiPokemonsDataSource(apiInterface)
    fun getLocalPokemonsDataSource(): DaoPokemons = dataBase.daoPokemons()
}