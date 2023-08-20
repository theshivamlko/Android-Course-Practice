package com.example.librayappcompose.roomdb

import com.example.librayappcompose.interfaces.IAppRoomDB
import kotlinx.coroutines.flow.Flow

class RoomDBRepository(val appDatabase: AppDatabase):IAppRoomDB {
    override   fun getAllBooks(): Flow<List<BookEntity>> {
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