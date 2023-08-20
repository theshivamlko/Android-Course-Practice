package com.example.mycoursesapp

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycoursesapp.ui.theme.MyCoursesAppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            MyCoursesAppTheme {


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {

                    // NaController
                    val navController = rememberNavController()

                    // NavHost
                    NavHost(navController = navController, startDestination = "HomeScreen") {

                        // Nav Graph Builder

                        composable("HomeScreen") {
                            HomeScreen(onDetailClick = {
                                navController.navigate("DetailsScreen/courseTitle=$it")
                            }, onAboutClick = {
                                println("HomeScreen AboutScreen")
                                navController.navigate("AboutScreen")
                            })
                        }

                        composable("AboutScreen") {
                            AboutScreen(onNavigateUp = {
                                println("AboutScreen pop")
                                navController.popBackStack()
                            })
                        }


                        composable("DetailsScreen/courseTitle={courseTitle}",
                            // Register upcoming arguments
                            arguments = listOf(
                                navArgument("courseTitle") {
                                    type = NavType.StringType
                                    nullable = true
                                }

                            )) {

                            val args = requireNotNull(it.arguments)
                            val title = args.getString("courseTitle")


                            DetailScreen(title = title!!) {

                                navController.popBackStack()
                            }
                        }
                    }

                }



            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onDetailClick: (title: String) -> Unit, onAboutClick: () -> Unit) {

    Scaffold(topBar = {
        HomeAppBar({
            println("AboutScreen Open")
            onAboutClick()
        })
    }) {

        LazyColumn(contentPadding = it) {
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }

            items(list) {
                CourseCard(it) {
                    onDetailClick(it.title)
                  // val navController= rememberNavController()

                }

            }
        }
    }

}


@Composable
fun HomeAppBar(onAboutClick: () -> Unit) {

    Box(Modifier.background(Color.White)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Text(text = "Udemy Courses", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.weight(1f))
            TextButton(onClick = onAboutClick) {
                Text(text = "About", fontSize = 24.sp)

            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onNavigateUp: () -> Unit) {

    Scaffold { it ->
        Column(Modifier.padding(it)) {
            AppBar(title = "About", onNavigateUp)
            Spacer(modifier = Modifier.height(20.dp))
            Column(Modifier.padding(16.dp)) {
                Text(text = "Navigation in JetPack")
                Spacer(modifier = Modifier.height(20.dp))

                val udemy_link = LocalUriHandler.current

                Button(onClick = { udemy_link.openUri("https://www.udemy.com/course/kotlin-masterclass-learn-kotlin-from-zero-to-advanced/") }) {
                    Text(text = "View Course")
                }
            }
        }
    }

}

@Composable
fun AppBar(title: String, onNavigateUp: () -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        IconButton(onClick = { onNavigateUp() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.Black,
                contentDescription = null
            )
        }
        Text(text = title)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseCard(item: CourseModel, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), onClick = onClick
    ) {

        Column() {
            Image(
                painterResource(id = item.thumbnail),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = item.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.body, maxLines = 1, style = MaterialTheme.typography.bodySmall)

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(title: String, onNavigateUp: () -> Unit) {

    val course = list.first { it.title == title }

    Scaffold {

        Column(Modifier.padding(it)) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                }
            }

            Text(text = course.title, fontSize = 40.sp)

            Image(
                painter = painterResource(id = course.thumbnail),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Text(text = course.body, fontSize = 40.sp, modifier = Modifier.fillMaxSize())


        }

    }

}
