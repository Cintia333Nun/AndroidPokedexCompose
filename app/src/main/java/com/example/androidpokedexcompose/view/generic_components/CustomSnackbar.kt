package com.example.androidpokedexcompose.view.generic_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidpokedexcompose.theme.colorPrimary
import com.example.androidpokedexcompose.theme.textWhiteColor

@Composable
fun CustomSnackbar(
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    val snackbarHostState = remember { SnackbarHostState() }

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.padding(16.dp),
    ) {
        Snackbar(
            modifier = Modifier.padding(8.dp),
            containerColor = colorPrimary,
            contentColor = textWhiteColor,
        ) {
            Text(text = message)
        }
    }

    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar(message = message, duration = duration)
    }
}
