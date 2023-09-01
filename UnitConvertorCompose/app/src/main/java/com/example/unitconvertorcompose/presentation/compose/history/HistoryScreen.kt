package com.example.unitconvertorcompose.presentation.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.unitconvertorcompose.data.model.ConversionResult


@Composable
fun HistoryScreen(
    state: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    delete:(ConversionResult)->Unit

) {

    LazyColumn {

        items(
            items = state.value,
            key = {item -> item.id }

        ){

            HistoryItem(conversionResult = it) {

                delete(it)

            }
        }
    }


}