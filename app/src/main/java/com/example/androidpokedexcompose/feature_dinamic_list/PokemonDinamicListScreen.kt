package com.example.androidpokedexcompose.feature_dinamic_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidpokedexcompose.data.utils.ParamsPokemonData
import com.example.androidpokedexcompose.theme.colorCard
import com.example.androidpokedexcompose.theme.colorPrimary
import com.example.androidpokedexcompose.theme.textWhiteColor
import com.example.androidpokedexcompose.view.generic_components.BackIcon
import com.example.androidpokedexcompose.view.view_model.PokedexViewModel

@Composable
fun PokemonDinamicListScreen(
    name: String, type: String, urlEndPoint: String,
    onBackPressed: () -> Unit, viewModel: PokedexViewModel
) {
    if(type == ParamsPokemonData.POKEDEX_TYPE_POWERS) {
        viewModel.getPowers(name = name)
    } else {
        viewModel.getEvolutions(url = urlEndPoint)
    }
    val context = LocalContext.current
    val pokemonsState by viewModel.dynamicList.collectAsStateWithLifecycle()
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorPrimary)
                .align(Alignment.TopCenter)
                .padding(all = 20.dp)
        ) {
            BackIcon(
                color = colorCard,
                onClick = onBackPressed
            )
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize()
            ) {
                items(pokemonsState) { item ->
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = item,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        color = textWhiteColor
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PokemonDataScreenPreview() {
    PokemonDinamicListScreen(
        name = "", type = "", urlEndPoint = "",
        onBackPressed = {}, viewModel = viewModel()
    )
}