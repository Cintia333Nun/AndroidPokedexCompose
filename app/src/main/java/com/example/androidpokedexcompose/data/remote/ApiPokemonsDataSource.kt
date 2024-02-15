package com.example.androidpokedexcompose.data.remote

import com.example.androidpokedexcompose.data.remote.models.DataPokemon
import com.example.androidpokedexcompose.data.remote.models.ImagePokemon
import com.example.androidpokedexcompose.data.remote.models.Pokemon
import com.example.androidpokedexcompose.feature_dinamic_list.models.Evolutions
import com.example.androidpokedexcompose.feature_dinamic_list.models.Powers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiPokemonsDataSource(private val apiInterface: ApiPokemonsInterface) {
    suspend fun getPokemons(limit: Int): Flow<Result<List<Pokemon>>> = flow {
        try {
            val response = apiInterface.getPokemons(limit)
            val pokemons = response.results
            emit(Result.success(pokemons))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }

    suspend fun getPokemonsImage(url: String): Flow<Result<ImagePokemon>> = flow {
        try {
            val urlImage = apiInterface.getImageUrl(url)
            emit(Result.success(urlImage))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }

    suspend fun getDataSelectedPokemon(name: String): Flow<Result<DataPokemon>> = flow {
        try {
            val dataPokemon = apiInterface.getDataSelectedPokemon(name)
            emit(Result.success(dataPokemon))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }

    suspend fun getPowers(name: String): Flow<Result<Powers>> = flow {
        try {
            val powers = apiInterface.getPowers(name)
            emit(Result.success(powers))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }

    suspend fun getEvolutions(url: String): Flow<Result<Evolutions>> = flow {
        try {
            val evolutions = apiInterface.getEvolutions(url)
            emit(Result.success(evolutions))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }
}