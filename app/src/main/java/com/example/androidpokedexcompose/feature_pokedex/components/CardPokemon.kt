package com.example.androidpokedexcompose.feature_pokedex.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.data.utils.DestinationsUtils
import com.example.androidpokedexcompose.data.remote.models.Pokemon
import com.example.androidpokedexcompose.data.utils.Utils
import com.example.androidpokedexcompose.theme.colorGray
import com.example.androidpokedexcompose.theme.colorPrimary
import com.example.androidpokedexcompose.theme.textWhiteColor
import com.example.androidpokedexcompose.view.generic_components.ImageWithCoil
import com.example.androidpokedexcompose.view.generic_components.StarIcon
import com.example.androidpokedexcompose.view.view_model.PokedexViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CardPokemon(viewModel: PokedexViewModel, item: Pokemon, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { expanded = true },
                    onTap = {
                        try {
                            CoroutineScope(Dispatchers.IO).launch {
                                if (Utils.startGooglePing(context)) {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        navController.navigate(
                                            "${DestinationsUtils.POKEMON_DATA}/${item.name}/?url=${item.finalImage ?: ""}"
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
                        } catch (exception: Exception) {
                            //Log.e("CardPokemon", "CardPokemon: ", exception)
                        }
                    }
                )
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
                .padding(all = 20.dp)
                .fillMaxWidth()
        ) {
            ImageWithCoil(key = item.name, url = item.finalImage ?: "")
            Text(
                text = item.name,  modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                fontSize = 18.sp, color = MaterialTheme.colorScheme.outline
            )
            if (item.isFavorite) StarIcon(color = colorPrimary)
            else StarIcon(color = colorGray)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(color = colorPrimary)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        stringResource(id = R.string.add_pokemon),
                        color = textWhiteColor
                    ) },
                onClick = {
                    item.isFavorite = !item.isFavorite
                    viewModel.updatePokemonFav(
                        name = item.name, status = item.isFavorite
                    )
                    expanded = false
                }
            )
        }
    }
}