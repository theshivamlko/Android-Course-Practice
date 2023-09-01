package com.example.unitconvertorcompose.utils

import android.content.Context
import com.example.unitconvertorcompose.data.roomdb.RoomDBService
import com.example.unitconvertorcompose.domain.repository.ConvertorRepositoryImpl
import com.example.unitconvertorcompose.presentation.compose.convertor.ConvertorViewModelFactory

class CreateInstance {


    companion object{
        fun createFactoryInstances(context:Context): ConvertorViewModelFactory {
            val db= RoomDBService.getInstance(context)
            val dao=db.convertorDAO()
            val repositoryImpl= ConvertorRepositoryImpl(dao)
            val factory= ConvertorViewModelFactory(repositoryImpl = repositoryImpl)

            return factory
        }
    }

}