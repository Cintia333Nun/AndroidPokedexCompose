package com.example.androidpokedexcompose.view.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpokedexcompose.data.local.EntityPokemon
import com.example.androidpokedexcompose.data.pojos.AlertData
import com.example.androidpokedexcompose.data.pojos.CustomAlerts
import com.example.androidpokedexcompose.data.pojos.TypesAlerts
import com.example.androidpokedexcompose.data.repository.PokemonsRepository
import com.example.androidpokedexcompose.data.remote.models.DataPokemon
import com.example.androidpokedexcompose.data.remote.models.Pokemon
import com.example.androidpokedexcompose.data.utils.Utils
import com.example.androidpokedexcompose.feature_dinamic_list.models.Chain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor (repository: PokemonsRepository): ViewModel() {
    companion object {
        const val LIMIT_SERVICE = 151
    }
    private val apiPokemonsDataSource = repository.getRemotePokemonDataSource()
    private val dbPokemonsDataSource = repository.getLocalPokemonsDataSource()

    private val _pokemons = MutableStateFlow(emptyList<Pokemon>())
    val pokemons = _pokemons.asStateFlow()

    private val _pokemonData = MutableStateFlow(DataPokemon())
    val pokemonData = _pokemonData.asStateFlow()

    private val _dinimicList = MutableStateFlow(emptyList<String>())
    val dynamicList = _dinimicList.asStateFlow()

    private val _showAlert = MutableStateFlow(CustomAlerts(isVisible = false))
    val showAlert = _showAlert.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private fun setLoading(status: Boolean) {
        _isLoading.value = status
    }

    fun validateDownloadData() {
        setShowAlert(CustomAlerts(isVisible = false))
        val existData = dbPokemonsDataSource.getPokemonCount()
        if (existData == 0) downloadPokemonsAndImages()
        else {
            if (existData == 151) {
                showAlertData(
                    "Ya cuentas con 151 pokemons en tu pokedex, ¿Quieres actualizar su información?",
                )
            } else {
                showAlertData(
                    "Notamos que no tines los pokemones totales, cuentas con ${pokemons.value.size} ¿Quieres actualizar su información?",
                )
            }
        }
    }

    private fun showAlertData(message: String) {
        setShowAlert(
            CustomAlerts(
                type = TypesAlerts.WIFI_STATUS, isVisible = true, alertData = AlertData(
                    title = "Actualizar pokemones",
                    message = message,
                    confirmButtonText = "No gracias",
                    dismissButtonText = "Actualizar",
                    callback = {
                        _pokemons.value = dbPokemonsDataSource.getAllPokemons().map { data ->
                            Pokemon(
                                name = data.name, url = data.url,
                                finalImage = data.urlImage, isFavorite = data.isFav
                            )
                        }
                        setShowAlert(CustomAlerts(isVisible = false))
                    },
                    onDismiss = {
                        downloadPokemonsAndImages()
                        setShowAlert(CustomAlerts(isVisible = false))
                    }
                )
            )
        )
    }

    private fun setShowAlert(alert: CustomAlerts) {
        _showAlert.value = alert
    }

    private fun downloadPokemonsAndImages() {
        viewModelScope.launch(Dispatchers.IO) {
            apiPokemonsDataSource.getPokemons(LIMIT_SERVICE).collect { result ->
                if(result.isSuccess) {
                    val tempList = result.getOrNull()
                    if (!tempList.isNullOrEmpty()) {
                        _pokemons.value = tempList
                        tempList.forEachIndexed { index, pokemon ->
                            downloadPokemonsImage(pokemon.url, index)
                        }
                        saveLocalPokemonsFromService()
                        //setLoading(status = true)
                        //setLoading(status = false)
                    }
                }
            }
        }
    }

    private suspend fun downloadPokemonsImage(url: String, index: Int) {
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

    private fun saveLocalPokemonsFromService() {
        val entities = pokemons.value.map { pokemon ->
            val isFav = dbPokemonsDataSource.getIsFavPokemon(pokemon.name)
            val urlImage = pokemon.finalImage ?: ""

            EntityPokemon (
               name = pokemon.name, url = pokemon.url, urlImage = urlImage, isFav = isFav
            )
        }
        dbPokemonsDataSource.insertPokemons(entities)
    }

    fun updatePokemonFav(name: String, status: Boolean) {
        dbPokemonsDataSource.updateFavPokemon(name = name, isFav = status)
    }

    override fun onCleared() {
        super.onCleared()
        Utils.freeMemory()
    }

    /*fun getPokemonsFromLocal(): Flow<PagingData<EntityPokemon>> {
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
    }*/
}