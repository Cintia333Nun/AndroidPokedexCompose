package com.example.androidpokedexcompose.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.data.local.Room
import com.example.androidpokedexcompose.data.remote.Retrofit
import com.example.androidpokedexcompose.data.repository.PokemonsRepository
import com.example.androidpokedexcompose.data.utils.DestinationsUtils
import com.example.androidpokedexcompose.data.utils.ParamsPokemonData
import com.example.androidpokedexcompose.data.utils.RoutesUtils
import com.example.androidpokedexcompose.data.utils.getViewModel
import com.example.androidpokedexcompose.feature_data_pokemon.GenericDataPokemonScreen
import com.example.androidpokedexcompose.view.view_model.PokedexViewModel
import com.example.androidpokedexcompose.feature_dinamic_list.PokemonDinamicListScreen
import com.example.androidpokedexcompose.feature_pokedex.PokedexScreen
import com.example.androidpokedexcompose.theme.AndroidPokedexComposeTheme

class PokedexActivity : ComponentActivity() {
    private val viewModel: PokedexViewModel by lazy {
        getViewModel {
            PokedexViewModel(
                PokemonsRepository(
                    Retrofit.provideApiRest(getString(R.string.end_point)),
                    Room.provideRoomDatabase(application)
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackCallback = { onBackPressedDispatcher.onBackPressed() }
        viewModel.getPokemons(151)
        setContent {
            AddNavigationPokedex(viewModel, onBackCallback)
        }
    }
}

@Composable
fun AddNavigationPokedex(viewModel: PokedexViewModel, onBackPressed: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationsUtils.POKEDEX) {
        composable(route = DestinationsUtils.POKEDEX) {
            AndroidPokedexComposeTheme {
                PokedexScreen(viewModel = viewModel, navController = navController)
            }
        }
        composable( route = RoutesUtils.POKEDEX_TO_GENERIC_DATA ) {
            val name = it.arguments?.getString(ParamsPokemonData.POKEDEX_NAME) ?: ""
            val url = it.arguments?.getString(ParamsPokemonData.POKEDEX_URL) ?: ""
            if (name.trim().isNotEmpty() && url.trim().isNotEmpty()) {
                viewModel.getDataSelectedPokemon(name)
                AndroidPokedexComposeTheme {
                    GenericDataPokemonScreen(
                        viewModel = viewModel, navController = navController, name = name, url = url
                    )
                }
            }
        }
        composable(route = RoutesUtils.GENERIC_DATA_TO_POKEMON_DATA) {
            val name = it.arguments?.getString(ParamsPokemonData.POKEDEX_NAME) ?: ""
            val type = it.arguments?.getString(ParamsPokemonData.POKEDEX_DATA_TYPE) ?: ""
            val url = it.arguments?.getString(ParamsPokemonData.POKEDEX_URL) ?: ""
            if (name.trim().isNotEmpty() && type.trim().isNotEmpty()) {
                AndroidPokedexComposeTheme {
                    PokemonDinamicListScreen(
                        name = name, type = type, urlEndPoint = url,
                        onBackPressed = onBackPressed, viewModel = viewModel
                    )
                }
            }
        }
    }
}