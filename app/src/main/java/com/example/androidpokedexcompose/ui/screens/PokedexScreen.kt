package com.example.androidpokedexcompose.ui.screens

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.data.model.Pokemon
import com.example.androidpokedexcompose.ui.components.ListOfPokemons
import com.example.androidpokedexcompose.ui.components.SemiCircle
import com.example.androidpokedexcompose.ui.theme.textColor

@Composable
fun PokedexScreen() {
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
            Row(modifier = Modifier.padding(top = 16.dp)) {
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
            ListOfPokemons(data = dataList)
        }
    }
}