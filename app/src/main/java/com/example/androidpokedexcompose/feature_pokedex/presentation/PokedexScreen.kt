package com.example.androidpokedexcompose.feature_pokedex.presentation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.feature_data_pokemon.data.model.Pokemon
import com.example.androidpokedexcompose.feature_pokedex.presentation.components.BackDialog
import com.example.androidpokedexcompose.feature_pokedex.presentation.components.ListOfPokemons
import com.example.androidpokedexcompose.view.generic_components.SemiCircle
import com.example.androidpokedexcompose.theme.AndroidPokedexComposeTheme
import com.example.androidpokedexcompose.theme.textColor

@Composable
fun PokedexScreen(navController: NavController) {
    val dataList = listOf(
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", false),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", true),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", false),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", false),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", true),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", false),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", false),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", true),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", false),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", false),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", true),
        Pokemon ("bulbasaur", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", false),
    )
    Box {
        SemiCircle()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)) {
                Text(
                    text = "Pokedex",
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 46.sp,
                )
                Image(
                    painter = painterResource(id = R.drawable.pokedex),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(30F)
                        .width(100.dp)
                        .height(100.dp)
                )
            }
            ListOfPokemons(data = dataList, navController)
        }
        BackDialog()
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexPreview() {
    AndroidPokedexComposeTheme {
        PokedexScreen(navController = rememberNavController())
    }
}