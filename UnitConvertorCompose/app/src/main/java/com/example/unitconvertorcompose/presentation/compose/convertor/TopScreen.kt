package com.example.unitconvertorcompose.presentation.compose.convertor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.unitconvertorcompose.data.model.Conversion


@Composable
fun TopScreen(
    list: List<Conversion>,
    save: (String, String) -> Unit
) {
    val selectConversion: MutableState<Conversion?> = remember {
        mutableStateOf(null)
    }

    val inputText: MutableState<String> = remember {
        mutableStateOf("")
    }

    var typedValue by remember {
        mutableStateOf("0.0")
    }
    var outputResult by remember {
        mutableStateOf("")
    }

    ConversionMenu(list) {
        selectConversion.value = it
        typedValue = "0.0"

    }
    selectConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText) { input ->

            println(input)
            typedValue = input

            if (typedValue != "0.0") {
                val dInput = typedValue.toDouble()
                val mulitply = selectConversion.value!!.multiplyBy
                var result = dInput * mulitply
                println(result)
                result = Math.ceil(result)
                println(result)

                val message1 = "${typedValue} ${selectConversion.value!!.convertFrom}"
                val message2 = "${result} ${selectConversion.value!!.convertTo}\n"

                inputText.value=""

                outputResult = "$message1 to $message2"


                save(message1, message2)
            }
        }
    }

    if (outputResult.isNotEmpty()) {

        ResultBlock(output = outputResult)
    }


}