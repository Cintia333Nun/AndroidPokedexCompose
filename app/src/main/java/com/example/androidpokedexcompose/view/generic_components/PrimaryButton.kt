package com.example.androidpokedexcompose.view.generic_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidpokedexcompose.theme.colorPrimary
import com.example.androidpokedexcompose.theme.textWhiteColor

@Composable
fun PrimaryButton(
    text: String,
    vertical: Int, horizontal: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = textWhiteColor,
            containerColor = colorPrimary
        ),
        modifier = Modifier.fillMaxWidth().padding(vertical = vertical.dp, horizontal = horizontal.dp)
    ) {
        Text(text = text)
    }
}