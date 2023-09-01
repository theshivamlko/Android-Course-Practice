package com.example.unitconvertorcompose.presentation.compose.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertorcompose.data.model.ConversionResult


@Composable
fun HistoryItem(conversionResult: ConversionResult, close: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(2.dp, color = Color.DarkGray)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween


    ) {

        Text(
            text = "${conversionResult.convertFrom} to ${conversionResult.convertTo}",
            style = TextStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )

        IconButton(onClick = { close() }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = null)

        }


    }
}