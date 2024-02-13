package com.example.androidpokedexcompose.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidpokedexcompose.data.model.Pokemon
import com.example.androidpokedexcompose.ui.theme.colorCard
import com.example.androidpokedexcompose.ui.theme.colorGray
import com.example.androidpokedexcompose.ui.theme.colorPrimary

@Composable
fun CardPokemon(item: Pokemon) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Add to favorites") },
            onClick = {
                Toast.makeText(context, "Favs", Toast.LENGTH_SHORT).show()
                expanded = false
            }
        )
    }

    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        expanded = true
                    }
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(colorCard, shape = RoundedCornerShape(8.dp))
                .padding(all = 20.dp)
        ) {
            ImageWithCoil(url = item.url)
            Text(text = item.name,  modifier = Modifier.padding(start = 10.dp, end = 10.dp), fontSize = 18.sp)
            if (item.isFavorite) StarIcon(color = colorPrimary)
            else StarIcon(color = colorGray)
        }
    }
}
