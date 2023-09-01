package com.example.unitconvertorcompose.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.unitconvertorcompose.data.model.Conversion


@Composable
fun TopScreen(list: List<Conversion>) {
    val selectConversion: MutableState<Conversion?> = remember {
        mutableStateOf(null)
    }

    val inputText: MutableState<String> = remember {
        mutableStateOf("")
    }

    var typedValue by remember {
        mutableStateOf("0.0")
    }

    ConversionMenu(list) {
        selectConversion.value = it

    }
    selectConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText) { input ->

            println(input)
            typedValue = input
        }
    }

    if (typedValue != "0.0") {
        val dInput = typedValue.toDouble()
        val mulitply = selectConversion.value!!.multiplyBy
        var result = dInput * mulitply
        println(result)
        result = Math.ceil(result)
        println(result)

        val message="${typedValue} ${selectConversion.value!!.convertFrom} " +
                "to  " +
                "${result} ${selectConversion.value!!.convertTo}\n"

        ResultBlock(output = message)
    }


}