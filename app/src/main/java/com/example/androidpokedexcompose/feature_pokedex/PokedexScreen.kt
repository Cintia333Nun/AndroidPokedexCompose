package com.example.androidpokedexcompose.feature_pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.feature_pokedex.components.BackDialog
import com.example.androidpokedexcompose.feature_pokedex.components.ListOfPokemons
import com.example.androidpokedexcompose.view.generic_components.SemiCircle
import com.example.androidpokedexcompose.theme.AndroidPokedexComposeTheme
import com.example.androidpokedexcompose.view.view_model.PokedexViewModel

@Composable
fun PokedexScreen(viewModel: PokedexViewModel, navController: NavController) {
    val pokemonsState by viewModel.pokemons.collectAsStateWithLifecycle()

    Box {
        SemiCircle()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.pokedex),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            ListOfPokemons(data = pokemonsState, navController = navController)
        }
        BackDialog()
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexPreview() {
    AndroidPokedexComposeTheme {
        PokedexScreen(
            viewModel = viewModel(),
            navController = rememberNavController()
        )
    }
}