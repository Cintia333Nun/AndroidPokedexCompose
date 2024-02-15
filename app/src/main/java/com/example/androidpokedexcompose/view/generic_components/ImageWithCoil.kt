package com.example.androidpokedexcompose.view.generic_components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.example.androidpokedexcompose.R

@Composable
fun ImageWithCoil(key: String, url: String, width: Int = 80, height: Int = 80) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            //.memoryCachePolicy(CachePolicy.DISABLED)
            //.diskCachePolicy(CachePolicy.DISABLED)
            .crossfade(true)
            .diskCacheKey(key)
            .memoryCacheKey(key)
            .build(),
        placeholder = painterResource(R.drawable.pokeball),
        error = painterResource(R.drawable.pokeball),
        //imageLoader = ImageLoader(LocalContext.current),
        contentDescription = stringResource(R.string.whos_that_pokemon),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .width(width.dp)
            .height(height.dp)
    )
}