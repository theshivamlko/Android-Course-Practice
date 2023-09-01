package com.example.unitconvertorcompose.presentation.compose.convertor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unitconvertorcompose.domain.repository.ConvertorRepositoryImpl

class ConvertorViewModelFactory(val repositoryImpl: ConvertorRepositoryImpl) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConvertorViewModel(repositoryImpl) as T
    }

}