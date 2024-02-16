package com.example.androidpokedexcompose.feature_pokedex.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.data.utils.Utils
import com.example.androidpokedexcompose.view.generic_components.WifiAlert
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
            Utils.closeApp(context)
        }
        showAlert = false
    }

    BackHandler {
        showAlert = true
    }

    if (showAlert) {
        WifiAlert(
            title = stringResource(id = R.string.close),
            message = stringResource(id = R.string.is_ok_close),
            confirmButtonText = stringResource(id = R.string.close_app),
            dismissButtonText = stringResource(id = R.string.continue_button),
            onDismiss = onDismiss,
            callback = callBack,
        )
    }
}