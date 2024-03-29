package com.example.androidpokedexcompose.feature_data_pokemon

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.data.remote.models.Egg
import com.example.androidpokedexcompose.data.utils.DestinationsUtils
import com.example.androidpokedexcompose.data.utils.ParamsPokemonData
import com.example.androidpokedexcompose.data.utils.Utils
import com.example.androidpokedexcompose.feature_data_pokemon.components.DataPokemon
import com.example.androidpokedexcompose.view.generic_components.ImageWithCoil
import com.example.androidpokedexcompose.view.generic_components.PrimaryButton
import com.example.androidpokedexcompose.view.generic_components.SemiCircle
import com.example.androidpokedexcompose.theme.AndroidPokedexComposeTheme
import com.example.androidpokedexcompose.theme.colorPrimary
import com.example.androidpokedexcompose.theme.textWhiteColor
import com.example.androidpokedexcompose.view.view_model.PokedexViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StringBuilder

@Composable
fun GenericDataPokemonScreen(
    viewModel: PokedexViewModel,
    name: String, url: String,
    navController: NavController
) {
    val pokemonDataState by viewModel.pokemonData.collectAsStateWithLifecycle()
    Box(
        Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        val context = LocalContext.current
        SemiCircle(colorPrimary)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 100.dp),
                text = name,
                color = textWhiteColor,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
            )
            ImageWithCoil(key = name, url = url, width = 180, height = 180)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        all = 30.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DataPokemon(
                    title = stringResource(id = R.string.hapiness),
                    description = pokemonDataState.baseHappiness.toString()
                )
                DataPokemon(
                    title = stringResource(id = R.string.ratio),
                    description = pokemonDataState.captureRate.toString()
                )
                DataPokemon(
                    title = stringResource(id = R.string.egg),
                    description = getDataEggFormat(pokemonDataState.eggGroups)
                )
                PrimaryButton(
                    text = stringResource(id = R.string.button_powers),
                    vertical = 10, horizontal = 10,
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            if (Utils.startGooglePing(context)) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    navController.navigate(
                                        "${DestinationsUtils.POKEMON_GENERAL}/" +
                                                "$name/${ParamsPokemonData.POKEDEX_TYPE_POWERS}/?url=NA"
                                    )
                                }
                            } else {
                                CoroutineScope(Dispatchers.Main).launch {
                                    Toast.makeText(
                                        context, context.getText(R.string.no_connection),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                )
                PrimaryButton(
                    text = stringResource(id = R.string.button_evolutions),
                    vertical = 0, horizontal = 10,
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            if (Utils.startGooglePing(context)) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    val endPoint = pokemonDataState.evolutionChain.url
                                    if (endPoint.trim().isNotEmpty()) {
                                        navController.navigate(
                                            "${DestinationsUtils.POKEMON_GENERAL}/" +
                                                    "$name/${ParamsPokemonData.POKEDEX_TYPE_EVOLUTIONS}/?url=${endPoint}"
                                        )
                                    }
                                }
                            } else {
                                CoroutineScope(Dispatchers.Main).launch {
                                    Toast.makeText(
                                        context, context.getText(R.string.no_connection),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

fun getDataEggFormat(eggGroups: List<Egg>): String {
    val stringBuilder = StringBuilder()
    eggGroups.forEachIndexed { index, egg ->
        if (index == eggGroups.size-1) stringBuilder.append(egg.name)
        else stringBuilder.append(egg.name).append(", ")
    }
    return stringBuilder.toString()
}

@Preview(showBackground = true)
@Composable
fun GenericDataPreview() {
    AndroidPokedexComposeTheme {
        GenericDataPokemonScreen(
            viewModel = viewModel(),
            name = "Charmander",
            url = "https://pokeapi.co/api/v2/pokemon/4/",
            navController = rememberNavController()
        )
    }
}