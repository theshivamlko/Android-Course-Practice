package com.example.unitconvertorcompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ResultBlock(output: String) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Column(modifier = Modifier.padding(10.dp)) {

            Text(
                text = output,
                fontSize = 25.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }

    }
}