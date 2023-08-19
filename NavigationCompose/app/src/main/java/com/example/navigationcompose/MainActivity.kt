package com.example.navigationcompose

import android.net.Uri
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
fun DisplayNavigation() {

    // NavController
    val navController = rememberNavController()

    // NavHost
    NavHost(
        navController = navController,
        startDestination = DestinationUtil.FirstScreen.route,
    ) {
        composable(
            route = DestinationUtil.FirstScreen.route,
            arguments = listOf(navArgument("counter") {
                type = NavType.StringType
                defaultValue = "N/A"
            })
        ) {
            println("FirstScreen1")
            val arg = it.arguments?.getString("counter")
            println("FirstScreen arg $arg")
            FirstScreen(navController)
        }



        composable(
            route = DestinationUtil.SecondScreen.route + "/{user}/{page}?num={counter}",
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
                defaultValue = "N/A"
            }, navArgument("page") {
                type = NavType.IntType
                defaultValue = -1
            }, navArgument("counter") {
                type = NavType.IntType
                defaultValue = -10
            })
        ) {
            val arg = it.arguments?.getString("user")
            var page = it.arguments?.getInt("page")
            var counter = it.arguments?.getInt("counter")
            println("SecondScreen1 $arg $page $counter")
            page = page!! + 1;

            SecondScreen(arg, navController)


        }
    }


}

@Composable
fun FirstScreen(navController: NavController) {
    println("FirstScreen2")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Button(
            onClick = {

                navController.navigate(route = DestinationUtil.SecondScreen.route + "/Shivam/50?num=101")

            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Welcome to First Screen")

        }
    }
}

@Composable
fun SecondScreen(user: String?, navController: NavController) {
    println("SecondScreen2")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Button(
            onClick = {
                //   navController.navigate(route = "First Screen")
                navController.popBackStack()
                // Remove Specific Route
                //    navController.popBackStack(DestinationUtil.SecondScreen.route, true)
            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Welcome to $user")

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