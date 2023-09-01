package com.example.unitconvertorcompose.presentation.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconvertorcompose.data.model.Conversion
import com.example.unitconvertorcompose.data.model.ConversionResult
import com.example.unitconvertorcompose.presentation.compose.convertor.ConvertorViewModel
import com.example.unitconvertorcompose.presentation.compose.convertor.ConvertorViewModelFactory
import com.example.unitconvertorcompose.presentation.compose.convertor.TopScreen
import com.example.unitconvertorcompose.presentation.compose.history.HistoryScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    factory: ConvertorViewModelFactory,
    modifier: Modifier = Modifier,
    convertorViewModel: ConvertorViewModel = viewModel(factory = factory)
) {

    val configuration = LocalConfiguration.current
    val list = convertorViewModel.getConversions()
    val historyList = convertorViewModel.getHistoryList().collectAsState(initial = emptyList())


    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {

            getLandScape(convertorViewModel = convertorViewModel, list, historyList)

        }

        Configuration.ORIENTATION_PORTRAIT -> {
            getPortrait(convertorViewModel = convertorViewModel, list, historyList)

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun getLandScape(
    convertorViewModel: ConvertorViewModel,
    list: List<Conversion>,
    historyList:  State<List<ConversionResult>>,
) {
    return Column {
        TopAppBar(
            title = {
                Text(
                    text = "Unit Convertor App", style = TextStyle(
                        color = Color.White
                    )
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Blue),
            actions = {
                IconButton(onClick = { convertorViewModel.deleteAllResults() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh, contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )
        Row(modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            TopScreen(
                list,
                convertorViewModel.selectConversion,
                convertorViewModel.inputText,
                convertorViewModel.typedValue,
                convertorViewModel.outputResult
            ) { s1, s2 ->
                convertorViewModel.addResult(s1, s2)

            }
            Spacer(modifier = Modifier.padding(20.dp))

            if (historyList.value.isNotEmpty()) {
                HistoryScreen(historyList) {
                    convertorViewModel.deleteResult(it)
                }
            } else {
                Text(text = "No data available")
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun getPortrait(
    convertorViewModel: ConvertorViewModel,
    list: List<Conversion>,
    historyList: State<List<ConversionResult>>,
) {
    return Column {
        TopAppBar(
            title = {
                Text(
                    text = "Unit Convertor App", style = TextStyle(
                        color = Color.White
                    )
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Blue),
            actions = {
                IconButton(onClick = { convertorViewModel.deleteAllResults() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh, contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )
        Column(modifier = Modifier.padding(20.dp)) {

            TopScreen(
                list,
                convertorViewModel.selectConversion,
                convertorViewModel.inputText,
                convertorViewModel.typedValue,
                convertorViewModel.outputResult
            ) { s1, s2 ->
                convertorViewModel.addResult(s1, s2)

            }
            Spacer(modifier = Modifier.padding(20.dp))

            if (historyList.value.isNotEmpty()) {
                HistoryScreen(historyList) {
                    convertorViewModel.deleteResult(it)
                }
            } else {
                Text(text = "No data available")
            }
        }

    }

}