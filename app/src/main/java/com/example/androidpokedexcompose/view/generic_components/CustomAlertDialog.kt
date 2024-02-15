package com.example.androidpokedexcompose.view.generic_components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidpokedexcompose.data.pojos.TypesAlerts
import com.example.androidpokedexcompose.theme.colorPrimary
import com.example.androidpokedexcompose.theme.textColor
import com.example.androidpokedexcompose.theme.textWhiteColor
import com.example.androidpokedexcompose.view.view_model.PokedexViewModel

@Composable
fun CustomAlertDialog(viewModel: PokedexViewModel) {
    val showAlert by viewModel.showAlert.collectAsStateWithLifecycle()
    if (showAlert.isVisible) {
        when(showAlert.type) {
            TypesAlerts.DEFAULT -> DefaultAlert(
                showAlert.alertData.title,
                showAlert.alertData.message,
                showAlert.alertData.confirmButtonText,
                showAlert.alertData.dismissButtonText,
                showAlert.alertData.onDismiss,
                showAlert.alertData.callback
            )
        }
    }
}

@Composable
fun DefaultAlert(
    title: String,
    message: String,
    confirmButtonText: String,
    dismissButtonText: String,
    onDismiss: () -> Unit,
    callback: () -> Unit,
) {
    AlertDialog(
        containerColor = colorPrimary,
        onDismissRequest = onDismiss,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title, textAlign = TextAlign.Center,
                color = textWhiteColor
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = message, textAlign = TextAlign.Center,
                color = textWhiteColor
            )
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = textWhiteColor, contentColor = textColor
                )
            ) {
                Text(dismissButtonText)
            }
        },
        confirmButton = {
            Button(
                onClick = callback,
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = textWhiteColor, contentColor = textColor
                )
            ) {
                Text(confirmButtonText)
            }
        },
    )
}
