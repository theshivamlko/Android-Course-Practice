package com.example.jetcomposeapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
                    Column(
                        modifier = Modifier
                            .background(color = Color.Cyan)
                            .verticalScroll(state = rememberScrollState()),
                    ) {
                        Greeting("Shivam")
                        DisplayText(4)
                    }

                    Column(
                        modifier = Modifier
                            .background(color = Color.Cyan)
                            .verticalScroll(state = rememberScrollState()),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Greeting("Shivam")
                        DisplayText(2)


                        Button(
                            onClick = {
                                println("Display")
                            },
                            shape = RoundedCornerShape(size = 20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                            elevation = ButtonDefaults.buttonElevation(
                                pressedElevation = 5.dp,
                                defaultElevation = 10.dp
                            )
                        ) {
                            Text(
                                text = "Display" +
                                        stringResource(id = R.string.app_name),
                                color = Color.Red,
                                fontSize = 30.sp,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    textDecoration = TextDecoration.LineThrough,
                                    background = Color.Yellow,
                                    fontWeight = FontWeight.Bold
                                ),

                                )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.app_store8),
                            contentDescription = "app_store8",
                            modifier = Modifier.size(100.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "app_store8"
                        )

                        Image(
                            painter = painterResource(id = R.drawable.pexels_photo),
                            contentDescription = "app_store8",
                            modifier = Modifier
                                .size(160.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 10.dp,
                                    color = Color.Red,
                                    shape =  CircleShape
                                )
                                .scale(1.2f)
                                .aspectRatio(9f/16f)
                                .blur(radius = 10.dp, edgeTreatment = BlurredEdgeTreatment(shape = RoundedCornerShape(percent = 5  )))

                        )


                    }


                }
            }
        }
    }
}


@Composable
fun DisplayText(num: Int) {

    for (i in 1..num) {
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