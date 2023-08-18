package com.example.jetcomposeapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.contentValuesOf
import com.example.jetcomposeapp.ui.theme.JetComposeAppTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            JetComposeAppTheme {
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
    val activity = (LocalContext.current as Activity)


    var selectedItem by remember {
        mutableStateOf(0)
    }
    var selectedBottom by remember {
        mutableStateOf(0)
    }

    Scaffold(topBar = {
        Surface(
            shadowElevation = 20.dp,
        ) {
            TopAppBar(
                title = { Text(text = "ABC") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    Color.Blue,
                    Color.Gray,
                    Color.Red,
                    Color.Green,
                    Color.Yellow //Add your own color here, just to clarify.
                ),
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {

                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "a")
                    }
                },

                actions = {
                    IconButton(
                        onClick = {
                            println("AccountCircle")
                        },

                        ) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "",
                            tint = Color.Gray
                        )


                    }


                },


                )
        }
    }, containerColor = Color.Yellow, bottomBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {

            NavigationBar(
                containerColor = Color.Blue,
                contentColor = Color.Magenta,
                tonalElevation = 2.dp,

                ) {

                NavigationBarItem(selected = true, label = { Text("HOME") }, onClick = {
                    println("")
                }, icon = {
                    Icon(
                        imageVector = Icons.Filled.AddCircle, contentDescription = ""
                    )
                })
                NavigationBarItem(selected = true, label = { Text("PROFILE") }, onClick = {
                    println("")
                }, icon = {
                    Icon(
                        imageVector = Icons.Filled.AddCircle, contentDescription = ""
                    )
                })
                NavigationBarItem(selected = true, label = { Text("SETTING") }, onClick = {
                    println("")
                }, icon = {
                    Icon(
                        imageVector = Icons.Filled.AddCircle, contentDescription = ""
                    )
                })
                NavigationBarItem(selected = true, label = { Text("ORDERS") }, onClick = {
                    println("")
                }, icon = {
                    Icon(
                        imageVector = Icons.Filled.AddCircle, contentDescription = ""
                    )
                })
            }
        }
    },/*  bottomBar = {
              BottomAppBar(containerColor=Color.Red, tonalElevation = 20.dp) {
                  Row (horizontalArrangement = Arrangement.SpaceEvenly){
                      repeat(5){

                          ButtonMenuItem("Button $it",it,selectedBottom==it,Icons.Outlined.AccountCircle){
                              selectedBottom=it
                          }
                      }
                  }


              }
          },*/



        content = {


            Box(modifier = Modifier.padding(it))

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

            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(vertical = 100.dp),
                    onClick = {
                        println("FloatingActionButton")
                    },


                    ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "")

                }
            }


        })


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JetComposeAppTheme {
        Greeting2("Android")
    }
}