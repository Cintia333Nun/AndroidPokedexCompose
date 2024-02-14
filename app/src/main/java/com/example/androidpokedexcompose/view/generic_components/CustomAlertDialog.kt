package com.example.androidpokedexcompose.view.generic_components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androidpokedexcompose.R
import com.example.androidpokedexcompose.theme.colorPrimary
import com.example.androidpokedexcompose.theme.textColor
import com.example.androidpokedexcompose.theme.textWhiteColor

@Composable
fun CustomAlertDialog(
    title: String,
    message: String,
    confirmButtonText: String,
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
                color = textColor
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = message, textAlign = TextAlign.Center,
                color = textColor
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
                Text(stringResource(R.string.close))
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