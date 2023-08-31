package com.example.jetcomposeapp

import android.app.NotificationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.RemoteInput
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetcomposeapp.example2.MyViewModel1
import com.example.jetcomposeapp.ui.theme.JetComposeAppTheme
import com.example.jetcomposeapp.ui.theme.navokiColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {


    suspend fun run() {


        lifecycleScope.launch(Dispatchers.IO) {
            delay(5000L)
            println("IO ${Thread.currentThread().name}")
            // here
            lifecycleScope.launch(Dispatchers.Unconfined) {
                println("Unconfined3 ${Thread.currentThread().name}")
                delay(5000L)
                println("Unconfined4 ${Thread.currentThread().name}")
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val str = intent.getStringExtra("page")

        val bundle=RemoteInput.getResultsFromIntent(intent)
        val search=bundle?.getString("key_search")


        val notificatioMng = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificatioMng.cancelAll()

        println("MainActivity intent $str  $search")

        /*  println("lifecycleScope 1")
          lifecycleScope.launch {
              println("lifecycleScope launch")
              run()
              println("lifecycleScope END")

          }*/

        println("MainActivity onCreate  ")
        setContent {
            println("MainActivity setContent  ")

            var user by remember {
                mutableStateOf(User(20, "Lucknow"))
            }

            JetComposeAppTheme {
                println("MainActivity JetComposeAppTheme  ")
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
                        listOf(1, 2, 3)
                        DisplayText(2)
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


                        var count = 0
                        val count2 by remember {
                            mutableStateOf(0)
                        }

                        var count3 by remember {
                            mutableStateOf(0)
                        }

                        Button(
                            onClick = {
                                println("Display")

                                user = User(10, "Jaunpur")
                                //   user

                                println("Display ${++count3}")
                            },
                            shape = RoundedCornerShape(size = 20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                            elevation = ButtonDefaults.buttonElevation(
                                pressedElevation = 5.dp,
                                defaultElevation = 10.dp
                            )
                        ) {
                            Text(
                                text = "${user.name}" +
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
                                    shape = CircleShape
                                )
                                .scale(1.2f)
                                .aspectRatio(9f / 16f)
                                .blur(
                                    radius = 2.dp,
                                    edgeTreatment = BlurredEdgeTreatment(
                                        shape = RoundedCornerShape(percent = 5)
                                    )
                                )

                                .aspectRatio(9f / 16f)
                                .blur(
                                    radius = 10.dp,
                                    edgeTreatment = BlurredEdgeTreatment(
                                        shape = RoundedCornerShape(percent = 5)
                                    )
                                )
                        )

                        var inputVal by remember {
                            mutableStateOf("")
                        }

                        BasicTextField(
                            value = inputVal, onValueChange = { text -> inputVal = text },

                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Search
                            ),

                            keyboardActions = KeyboardActions(onDone = {

                                println("onDone $this")
                            }),

                            modifier = Modifier.border(
                                width = 2.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(12.dp)
                            ),
                            decorationBox = { innerTextField ->
                                Row(
                                    Modifier
                                        .background(
                                            Color.LightGray,
                                            RoundedCornerShape(percent = 30)
                                        )
                                        .padding(16.dp)
                                ) {

                                    if (inputVal.isEmpty()) {
                                        Text("Label")
                                    }
                                    innerTextField()  //<-- Add this
                                }
                            },

                            )

                        Text(text = "Hello $inputVal")

                        val viewModel= viewModel<MyViewModel1>()
                        val value=viewModel.getDataFlow().collectAsState(initial = "").value

                        Button(onClick = {

                        },
                            modifier = Modifier.padding(vertical = 20.dp)) {
                            Text(text = "View Model $value")
                        }

                        Button(onClick = {

                        },
                            modifier = Modifier.padding(vertical = 20.dp)) {
                            Text(text = "State Flow $value")
                        }

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

data class User(val age: Int, val name: String)