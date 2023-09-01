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
    selectConversion: MutableState<Conversion?>,
      inputText:  MutableState<String>,
      typedValue: MutableState<String>,
      outputResult: MutableState<String>,
    save: (String, String) -> Unit
) {


    ConversionMenu(list) {
        selectConversion.value = it
        typedValue.value = "0.0"

    }
    selectConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText) { input ->

            println(input)
            typedValue.value  = input

            if (typedValue.value  != "0.0") {
                val dInput = typedValue.value .toDouble()
                val mulitply = selectConversion.value!!.multiplyBy
                var result = dInput * mulitply
                println(result)
                result = Math.ceil(result)
                println(result)

                val message1 = "${typedValue} ${selectConversion.value!!.convertFrom}"
                val message2 = "${result} ${selectConversion.value!!.convertTo}\n"

                inputText.value=""

                outputResult.value  = "$message1 to $message2"


                save(message1, message2)
            }
        }
    }

    if (outputResult.value .isNotEmpty()) {

        ResultBlock(output = outputResult.value )
    }


}