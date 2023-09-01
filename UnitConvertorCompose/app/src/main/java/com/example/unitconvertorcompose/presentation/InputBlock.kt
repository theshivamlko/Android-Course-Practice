package com.example.unitconvertorcompose.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertorcompose.data.model.Conversion


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier,
    context:Context= LocalContext.current,
    calculate:(String)->Unit
) {

    Column(modifier = Modifier.padding(20.dp)) {

        Row(modifier = Modifier.fillMaxWidth()) {

            TextField(
                value = inputText.value,
                onValueChange = { inputText.value = it },
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .background(Color.White),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    //containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                    containerColor = Color.White
                ),
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 20.sp)
            )

            Text(
                text = conversion.convertFrom, fontSize = 25.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.35f)
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))

        OutlinedButton(
            onClick = {
                inputText.value.let {
                    calculate(inputText.value) }
                },
            modifier = modifier.fillMaxWidth(1f)
            ) {

            Text(text = "Convert", fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )

        }
    }
}