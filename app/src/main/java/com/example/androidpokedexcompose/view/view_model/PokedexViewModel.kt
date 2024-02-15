package com.example.androidpokedexcompose.view.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidpokedexcompose.data.local.EntityPokemon
import com.example.androidpokedexcompose.data.repository.PokemonsRepository
import com.example.androidpokedexcompose.data.remote.models.DataPokemon
import com.example.androidpokedexcompose.data.remote.models.Pokemon
import com.example.androidpokedexcompose.feature_dinamic_list.models.Chain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//@HiltViewModel
//@Inject constructor()
class PokedexViewModel(repository: PokemonsRepository): ViewModel() {
    private val apiPokemonsDataSource = repository.getRemotePokemonDataSource()
    private val dbPokemonsDataSource = repository.getLocalPokemonsDataSource()

    private val _pokemons = MutableStateFlow(emptyList<Pokemon>())
    val pokemons = _pokemons.asStateFlow()

    private val _pokemonData = MutableStateFlow(DataPokemon())
    val pokemonData = _pokemonData.asStateFlow()

    private val _dinimicList = MutableStateFlow(emptyList<String>())
    val dynamicList = _dinimicList.asStateFlow()

    fun getPokemons(limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            apiPokemonsDataSource.getPokemons(limit).collect { result ->
                if(result.isSuccess) {
                    val tempList = result.getOrNull()
                    if (!tempList.isNullOrEmpty()) {
                        _pokemons.value = tempList
                        tempList.forEachIndexed { index, pokemon ->
                            getPokemonsImage(pokemon.url, index)
                        }
                    }
                }
            }
        }
    }

    private suspend fun getPokemonsImage(url: String, index: Int) {
        apiPokemonsDataSource.getPokemonsImage(url).collect { result ->
            if (result.isSuccess) {
                val updatedList = _pokemons.value.toMutableList()
                val image = result.getOrNull()
                image?.let {
                    updatedList[index].finalImage = image.sprites.frontDefault
                    _pokemons.value = updatedList
                }
            }
        }
    }

    fun getDataSelectedPokemon(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiPokemonsDataSource.getDataSelectedPokemon(name).collect { result ->
                if (result.isSuccess) {
                    val pokemonData = result.getOrNull()
                    pokemonData?.let {
                        _pokemonData.value = pokemonData
                    }
                }
            }
        }
    }

    fun getPowers(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiPokemonsDataSource.getPowers(name).collect { result ->
                if (result.isSuccess) {
                    val powers = result.getOrNull()
                    powers?.let {
                        val list = powers.abilities.map { item -> item.ability.name }
                        _dinimicList.value = list
                    }
                }
            }
        }
    }

    fun getEvolutions(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiPokemonsDataSource.getEvolutions(url).collect { result ->
                if (result.isSuccess) {
                    val evolutions = result.getOrNull()
                    evolutions?.let {
                        val evolutionsTotal = getAllEvolutionsChain(evolutions.chain)
                        _dinimicList.value = evolutionsTotal
                    }
                }
            }
        }
    }

    private fun getAllEvolutionsChain(chain: Chain?): MutableList<String> {
        val listEvolutions = mutableListOf<String>()
        getAllEvolutions(chain, listEvolutions)
        return listEvolutions
    }

    private fun getAllEvolutions(chain: Chain?, listEvolutions: MutableList<String>) {
        chain?.let {
            listEvolutions.add(chain.species.name)
            chain.evolvesTo?.forEach { newChains ->
                getAllEvolutions(newChains, listEvolutions)
            }
        }
    }

    fun getPokemonsFromLocal(): Flow<PagingData<EntityPokemon>> {
        val pagSize = 20
        val prefetchDistance = 15
        val maxElem = pagSize + (2* prefetchDistance)

        return Pager (
            config = PagingConfig(
                pageSize = pagSize,
                maxSize = maxElem,
                enablePlaceholders = true,
                prefetchDistance = prefetchDistance
            ),
            pagingSourceFactory = { dbPokemonsDataSource.getPagingPokemons() },
            initialKey = 0,
        ).flow.cachedIn(scope = viewModelScope)
    }
}