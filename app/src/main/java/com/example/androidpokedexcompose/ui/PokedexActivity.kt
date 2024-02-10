package com.example.androidpokedexcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.ui.theme.AndroidPokedexComposeTheme
import com.example.androidpokedexcompose.ui.theme.colorPrimary
import com.example.androidpokedexcompose.ui.theme.textColor

class PokedexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPokedexComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Pokedex()
                }
            }
        }
    }
}

@Composable
fun Pokedex() {
    Header(
        title = "Pokedex",
        image = painterResource(id = R.drawable.pokedex)
    )
}

@Composable
fun Header(title: String, image: Painter) {
    val dataList = listOf("Item 1", "Item 2", "Item 3", "Item 4")
    Box {
        SemiCircle()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = title,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 46.sp,
                )
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(30F)
                        .width(100.dp)
                        .height(100.dp)
                )
            }
            RecyclerView(data = dataList)
        }
    }
}

@Composable
fun RecyclerView(data: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
            .padding(top = 50.dp, start = 40.dp, end = 40.dp, bottom = 10.dp)
    ) {
        items(data) { item ->
            Text(text = item)
        }
    }
}

@Composable
fun SemiCircle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val arcHeight = 800F
        val offsetY = - (arcHeight / 2)

        drawArc(
            colorPrimary,
            0f,
            180f,
            useCenter = true,
            size = Size(canvasWidth, arcHeight),
            topLeft = Offset(x = 0F, y = offsetY)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PokedexPreview() {
    AndroidPokedexComposeTheme {
        Pokedex()
    }
}