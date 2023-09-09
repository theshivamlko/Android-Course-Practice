package com.anushka.effectsdemo1

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sideeffects.CounterEvents
import com.example.sideeffects.CounterViewModel
import com.example.sideeffects.UIEvents
import com.example.sideeffects.ui.theme.SideEffectsTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffectsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {

    var total by remember { mutableStateOf(0.0) }
    var input by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = total.hashCode()) {
        Toast.makeText(context, "Please, start counting..", Toast.LENGTH_SHORT).show()
    }
    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = "Total is ${total.toString()}",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.DarkGray
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            placeholder = { Text("Enter value here") },
            value = input,
            onValueChange = {
                input = it
            },
            textStyle = TextStyle(
                color = Color.LightGray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            label = { Text(text = "New count") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                total += input.toDouble()
                runBlocking {
                    Toast.makeText(context, "2222", Toast.LENGTH_SHORT).show()
                }

                Toast.makeText(context, "XYZ", Toast.LENGTH_SHORT).show()
                coroutineScope.launch {
                    Toast.makeText(context, "ABC", Toast.LENGTH_SHORT).show()

                }


            }
        ) {
            Text(
                text = "Count",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    counterViewModel: CounterViewModel = viewModel()
) {

    val screenState = counterViewModel.screenState.value

    LaunchedEffect(key1 = true) {
        counterViewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is UIEvents.ShowMessage -> {
                    println(event.message)
                }
            }

        }
    }

    Column {
        Text(text = screenState.displayResult, fontSize = 30.sp)

        OutlinedTextField(value = screenState.inputValue, onValueChange = {
            counterViewModel.onEvent(CounterEvents.ValueEntered(it))
        })

        if (screenState.isButtonShow) {
            Button(onClick = {
                counterViewModel.onEvent(CounterEvents.CountButtonClicked)

            }) {

                Text(text = "COUNT")
            }
        }

        Button(onClick = {
            counterViewModel.onEvent(CounterEvents.ResetButtonClicked)

        }) {

            Text(text = "RESET")
        }
    }

}

