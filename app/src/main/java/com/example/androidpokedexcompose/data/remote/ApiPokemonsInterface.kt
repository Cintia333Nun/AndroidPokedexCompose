package com.example.androidpokedexcompose.data.remote

import com.example.androidpokedexcompose.data.remote.models.DataPokemon
import com.example.androidpokedexcompose.data.remote.models.ImagePokemon
import com.example.androidpokedexcompose.data.remote.models.ResponseListPokemons
import com.example.androidpokedexcompose.feature_dinamic_list.models.Evolutions
import com.example.androidpokedexcompose.feature_dinamic_list.models.Powers
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiPokemonsInterface {
    @GET("api/v2/pokemon")
    suspend fun getPokemons(@Query("limit") limit: Int): ResponseListPokemons

    @GET
    suspend fun getImageUrl(@Url url: String): ImagePokemon

    @GET("api/v2/pokemon-species/{name}/")
    suspend fun getDataSelectedPokemon(@Path("name") name: String): DataPokemon

    @GET("api/v2/pokemon/{name}/")
    suspend fun getPowers(@Path("name") name: String): Powers

    @GET
    suspend fun getEvolutions(@Url url: String): Evolutions
}