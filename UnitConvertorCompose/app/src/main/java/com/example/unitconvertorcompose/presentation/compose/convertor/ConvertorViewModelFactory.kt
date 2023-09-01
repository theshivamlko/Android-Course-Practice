package com.example.unitconvertorcompose.presentation.compose.convertor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unitconvertorcompose.domain.repository.ConvertorRepositoryImpl
import com.example.unitconvertorcompose.domain.repository.IConvertorRepository

class ConvertorViewModelFactory constructor(val repository: IConvertorRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConvertorViewModel(repository) as T
    }

}