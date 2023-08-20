package com.example.librayappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.librayappcompose.api.ApiRepository
import com.example.librayappcompose.api.ApiService
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val roomDBRepository =
                        RoomDBRepository(AppDatabase.getInstance(LocalContext.current))


                    val booksApi = ApiService.provideApi(ApiService.provideRetrofit())
                    val apiRepository = ApiRepository(booksApi)

                    val bookViewModel = BookViewModel(roomDBRepository, apiRepository)
                    bookViewModel.getAllBooksFromApi()

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
    var selectedBookModel by remember {
        mutableStateOf(-1)
    }

    val books: List<BookEntity> by bookViewModel.booksList.collectAsState(initial = listOf())!!



    Column(
        horizontalAlignment = Alignment.CenterHorizontally
        //   modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {

        OutlinedTextField(value = input,
            onValueChange = { input = it },
            label = { Text(text = "Enter Book Name") },
            placeholder = { Text(text = "Your Book Name") })


        Button(onClick = {
            if (selectedBookModel == -1) {
                bookViewModel.addBook(BookEntity(0, input))
            } else {
                bookViewModel.updateBook(BookEntity(books.get(selectedBookModel).bookId, input))
                selectedBookModel = -1
            }
        }) {
            Text(
                text = if (selectedBookModel == -1) {
                    "Add Book"
                } else {
                    "Update Book"

                }

            )
        }

        Spacer(modifier = Modifier.padding(20.dp))


        println("LazyColumn ${books.size}")
        LazyColumn(content = {
            items(books.size) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${books.get(it).bookId} - ${books.get(it).bookName}",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    selectedBookModel = it
                                    input = books.get(it).bookName
                                }
                        )

                        IconButton(onClick = {
                            bookViewModel.deleteBook(books.get(it))
                        }) {
                            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
                        }
                    }

                    Divider(thickness = 2.dp)
                }
            }

        })
    }


}

