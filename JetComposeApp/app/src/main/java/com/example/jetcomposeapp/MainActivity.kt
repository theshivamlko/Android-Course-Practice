package com.example.jetcomposeapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcomposeapp.ui.theme.JetComposeAppTheme
import com.example.jetcomposeapp.ui.theme.navokiColor
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = navokiColor

                ) {
                    Column(modifier = Modifier.background(color = Color.Cyan) .verticalScroll(state = rememberScrollState()),) {

                        Greeting("Shivam")
                        DisplayText()
                    }
                }
            }
        }
    }
}


@Composable
fun DisplayText() {

    for (i in 1..10) {

        val onClickFun = {
            println("$i")

        }
        Text(
            text = "Display $i ${Random(0).nextInt(10)} \n " +
                    stringResource(id = R.string.app_name),
            color = Color.Red,
            fontSize = 30.sp,
            style = TextStyle(
                fontSize = 60.sp,
                textDecoration = TextDecoration.LineThrough, background = Color.Yellow,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(20.dp)
                .clickable(onClick = onClickFun)

        )
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
        text = "Hello $name!",
        modifier = modifier,
        color = Color.White,
        fontSize = TextUnit(20.0F, TextUnitType.Sp)
    )
}


// Not called anywhere
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetComposeAppTheme {
        Greeting("Preview")
    }
}