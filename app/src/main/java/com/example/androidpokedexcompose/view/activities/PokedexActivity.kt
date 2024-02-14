package com.example.androidpokedexcompose.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidpokedexcompose.data.utils.DestinationsUtils
import com.example.androidpokedexcompose.data.utils.ParamsPokemonData
import com.example.androidpokedexcompose.feature_data_pokemon.presentation.GenericDataScreen
import com.example.androidpokedexcompose.feature_data_pokemon.presentation.PokedexViewModel
import com.example.androidpokedexcompose.feature_dinamic_list.presentation.PokemonDataScreen
import com.example.androidpokedexcompose.feature_pokedex.presentation.PokedexScreen

class PokedexActivity : ComponentActivity() {
    private val viewModel  by viewModels<PokedexViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = DestinationsUtils.POKEDEX) {
                composable(route = DestinationsUtils.POKEDEX) {
                    PokedexScreen(navController)
                }
                composable(
                    route = "${DestinationsUtils.POKEMON_DATA}/" +
                            "{${ParamsPokemonData.POKEDEX_NAME}}/" +
                            "?url={${ParamsPokemonData.POKEDEX_URL}}"
                ) {
                    val name = it.arguments?.getString(ParamsPokemonData.POKEDEX_NAME) ?: ""
                    val url = it.arguments?.getString(ParamsPokemonData.POKEDEX_URL) ?: ""
                    if (name.trim().isNotEmpty() && url.trim().isNotEmpty()) {
                        GenericDataScreen(name = name, url = url)
                    }
                }
                composable(
                    route = "${DestinationsUtils.POKEMON_GENERAL}/" +
                        "{${ParamsPokemonData.POKEDEX_NAME}}/" +
                        "{${ParamsPokemonData.POKEDEX_DATA_TYPE}}"
                ) {
                    val name = it.arguments?.getString(ParamsPokemonData.POKEDEX_NAME) ?: ""
                    val type = it.arguments?.getString(ParamsPokemonData.POKEDEX_DATA_TYPE) ?: ""
                    if (name.trim().isNotEmpty() && type.trim().isNotEmpty()) {
                        PokemonDataScreen(name, type)
                    }
                }
            }
        }
    }
}