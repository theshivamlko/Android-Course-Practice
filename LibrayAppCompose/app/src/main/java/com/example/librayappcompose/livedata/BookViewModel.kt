package com.example.librayappcompose.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.librayappcompose.roomdb.BookEntity
import com.example.librayappcompose.roomdb.RoomDBRepository
import kotlinx.coroutines.launch

class BookViewModel(private val roomDBRepository: RoomDBRepository) : ViewModel() {

    var booksList: LiveData<List<BookEntity>>? = null

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

    fun getAllBooks() {
        viewModelScope.launch {
          //  booksList.value = roomDBRepository.getAllBooks()
          //  booksList.value
        }

    }

}