package com.example.androidpokedexcompose.feature_data_pokemon.presentation

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidpokedexcompose.view.generic_components.DataPokemon
import com.example.androidpokedexcompose.view.generic_components.ImageWithCoil
import com.example.androidpokedexcompose.view.generic_components.PrimaryButton
import com.example.androidpokedexcompose.view.generic_components.SemiCircle
import com.example.androidpokedexcompose.theme.AndroidPokedexComposeTheme
import com.example.androidpokedexcompose.theme.secondary
import com.example.androidpokedexcompose.theme.textWhiteColor

@Composable
fun GenericDataScreen(name: String, url: String) {
    Box {
        SemiCircle(secondary)
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 100.dp),
                text = name,
                color = textWhiteColor,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
            )
            ImageWithCoil(url = url, width = 180, height = 180)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        all = 30.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DataPokemon(title = "Felicidad", description = "Description")
                DataPokemon(title = "Ratio captura", description = "Description")
                DataPokemon(title = "Grupos huevo", description = "Description")
                PrimaryButton(
                    text = "Línea evolutiva",
                    vertical = 10, horizontal = 10,
                    onClick = {}
                )
                PrimaryButton(
                    text = "Habilidades",
                    vertical = 0, horizontal = 10,
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenericDataPreview() {
    AndroidPokedexComposeTheme {
        GenericDataScreen("Charmander", "https://pokeapi.co/api/v2/pokemon/4/")
    }
}