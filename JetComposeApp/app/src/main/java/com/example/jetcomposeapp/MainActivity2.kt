package com.example.jetcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.contentValuesOf
import com.example.jetcomposeapp.ui.theme.JetComposeAppTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {

    var selectedItem by remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(title =
            { Text(text = "ABC") }
            )
        },

        ) { contentPadding ->

        Box(modifier = Modifier.padding(contentPadding))

        Scaffold(
            topBar = {

            },

            ) { contentPadding ->

            Box(modifier = Modifier.padding(contentPadding))


            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedItem == 1,
                        onClick = { selectedItem = 1 },
                    )
                    Text(text = "Pizza")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedItem == 2,
                        onClick = { selectedItem = 2 },
                    )
                    Text(text = "Burger")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedItem == 3,
                        onClick = { selectedItem = 3 },
                    )
                    Text(text = "Pasta")
                }

                CircularProgressIndicator(
                    modifier = Modifier.size(size = 100.dp),
                    color = Color.Red,
                    strokeWidth = 10.dp,
                    //  progress = 0.5f
                )

            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JetComposeAppTheme {
        Greeting2("Android")
    }
}