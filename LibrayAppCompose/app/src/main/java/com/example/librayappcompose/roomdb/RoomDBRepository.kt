package com.example.librayappcompose.roomdb

import androidx.lifecycle.LiveData
import com.example.librayappcompose.interfaces.IAppData

class RoomDBRepository(val appDatabase: AppDatabase):IAppData {
    override suspend fun getAllBooks(): LiveData<List<BookEntity>> {
      return appDatabase.getDAO().getAllBooks()
    }

    override suspend fun insertBook(bookEntity: BookEntity) {
          appDatabase.getDAO().insertBook(bookEntity = bookEntity)

    }

    override suspend fun updateBook(bookEntity: BookEntity) {
        appDatabase.getDAO().updateBook(bookEntity = bookEntity)
    }

    override suspend fun deleteBook(bookEntity: BookEntity) {
        appDatabase.getDAO().deleteBook(bookEntity = bookEntity)
    }


}