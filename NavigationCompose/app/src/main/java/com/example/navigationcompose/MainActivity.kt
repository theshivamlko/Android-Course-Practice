package com.example.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationcompose.ui.theme.NavigationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   DisplayNavigation()
                }
            }
        }
    }
}

@Composable
fun DisplayNavigation( ) {

    // NavController
    val navController= rememberNavController()

    // NavHost
    NavHost(navController = navController,
        startDestination = "First Screen",
         ){
        composable(route="First Screen"){
            FirstScreen(navController)
        }
        composable(route="Second Screen"){
            SecondScreen(navController)
        }
    }



}

@Composable
fun FirstScreen(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {
        Button(onClick = {

            navController.navigate(route = "Second Screen")

        }, modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)) {
            Text(text = "Welcome to First Screen")

        }
    }
}
@Composable
fun SecondScreen(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)) {
        Button(
            onClick = {
             //   navController.navigate(route = "First Screen")
                navController.popBackStack()
            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Welcome to Second Screen")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationComposeTheme {
        DisplayNavigation()
    }
}