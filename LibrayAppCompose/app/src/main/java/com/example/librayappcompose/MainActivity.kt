package com.example.librayappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librayappcompose.livedata.BookViewModel
import com.example.librayappcompose.roomdb.AppDatabase
import com.example.librayappcompose.roomdb.BookEntity
import com.example.librayappcompose.roomdb.RoomDBRepository
import com.example.librayappcompose.ui.theme.LibrayAppComposeTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibrayAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val roomDBRepository =
                        RoomDBRepository(AppDatabase.getInstance(LocalContext.current))

                    val bookViewModel = BookViewModel(roomDBRepository)
                    MainScreen(bookViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(bookViewModel: BookViewModel) {
    var input by remember {
        mutableStateOf("")
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
        //   modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {

        OutlinedTextField(value = input, onValueChange = { input = it },
            label = { Text(text = "Enter Book Name") },
            placeholder = { Text(text = "Your Book Name") })


        Button(onClick = {
            bookViewModel.addBook(BookEntity(0, input))
        }) {
            Text(text = "Add Book")
        }
    }


}

