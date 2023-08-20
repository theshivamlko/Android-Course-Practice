package com.example.librayappcompose.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librayappcompose.api.ApiRepository
import com.example.librayappcompose.roomdb.BookEntity
import com.example.librayappcompose.roomdb.RoomDBRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class BookViewModel(
    private val roomDBRepository: RoomDBRepository,
    private val apiRepository: ApiRepository
) : ViewModel() {
    var booksList  = MutableStateFlow(emptyList<BookEntity>())



    fun addBook(bookEntity: BookEntity) {
        viewModelScope.launch {
            roomDBRepository.insertBook(bookEntity)
        }
    }

    fun updateBook(bookEntity: BookEntity) {
        viewModelScope.launch {
            roomDBRepository.updateBook(bookEntity)
        }
    }

    fun deleteBook(bookEntity: BookEntity) {
        viewModelScope.launch {
            roomDBRepository.deleteBook(bookEntity)
        }
    }

    fun getAllBooksFromApi() {
       viewModelScope.launch {
         val books=  apiRepository.getAllBooks()
           booksList.value=books
       }

    }/*
    fun getAllBooks(bookEntity: BookEntity) {
        booksList = flow {
            emit(apiRepository.getAllBooks())

        }

    }*/


    // From Local DB
   // var booksList = roomDBRepository.getAllBooks()


}