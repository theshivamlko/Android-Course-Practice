package com.example.jetcomposeapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun ButtonMenuItem(
    label: String,
    index: Int,
    selected: Boolean = false,
    icon: ImageVector,
    call: (index: Int) -> Unit
) {

    return IconButton(
        onClick = {
            call(index)
            println(index)
        },
    ) {
        Icon(
            imageVector = Icons.Filled.AddCircle, contentDescription = "",
            tint = if (selected) Color.Green else Color.Blue
        )
        Text(text = label, color = if (selected) Color.Green else Color.Blue)

    }
}