package com.example.jetcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcomposeapp.ui.theme.JetComposeAppTheme
import kotlin.random.Random

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //     NormalList("Android")
                    LazyList("Android")
                }
            }
        }
    }
}

@Composable
fun NormalList(name: String, modifier: Modifier = Modifier) {
    var list = arrayListOf<String>("Android", "iOS", "Windows", "Linux")
    repeat(10) {
        list.addAll(list)

    }

    Column {
        list.forEachIndexed { ind, it ->
            println(ind)
            Column {
                Text(
                    text = it, fontSize = 16.sp, modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(8.dp)
                )
                Divider(thickness = 1.dp, color = Color.Blue)
            }
        }
    }
}

@Composable
fun LazyList(name: String, modifier: Modifier = Modifier) {
    println("LazyList")

    var list = arrayListOf<String>("Android", "iOS", "Windows", "Linux")
    repeat(10) {
        list.addAll(list)

    }

    Column {
        LazyRow() {
            items(count = 5) {
                println(it)
                Row {
                    Text(
                        text = list.get(it), fontSize = 16.sp, modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(Color.Yellow)
                            .padding(8.dp)
                    )
                    Divider(thickness = 2.dp, color = Color.Blue)
                }
            }
        }

        LazyColumn() {
            items(count = 10) {
                println(it)
                Column {
                    Text(
                        text = list.get(it), fontSize = 16.sp, modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(8.dp)
                    )
                    Divider(thickness = 1.dp, color = Color.Blue)
                }
            }

        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),

            ) {

            items(count = 10) {
                println(it)
                Column {
                    Text(
                        text = list.get(it), fontSize = 16.sp, modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(
                                Color(
                                    red = Random.nextInt(0,255),
                                    green = Random.nextInt(0,255),
                                    blue = Random.nextInt(0,255),
                                )
                            )
                            .padding(8.dp)
                    )
                    Divider(thickness = 1.dp, color = Color.Blue)
                }

            }

        }
    }

}

