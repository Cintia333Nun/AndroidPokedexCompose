package com.example.androidpokedexcompose.feature_pokedex.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.androidpokedexcompose.data.utils.Utils
import com.example.androidpokedexcompose.view.generic_components.DefaultAlert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BackDialog() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var showAlert by remember { mutableStateOf(false) }
    val onDismiss = { showAlert = false }
    val callBack = {
        coroutineScope.launch(Dispatchers.IO) {
            Utils.clearCache(context)
            Utils.closeApp(context)
        }
        showAlert = false
    }

    BackHandler {
        showAlert = true
    }

    if (showAlert) {
        DefaultAlert(
            title = "Salir",
            message = "¿Estás seguro de que deseas salir?",
            confirmButtonText = "Cerrar app",
            dismissButtonText = "Continuar",
            onDismiss = onDismiss,
            callback = callBack,
        )
    }
}