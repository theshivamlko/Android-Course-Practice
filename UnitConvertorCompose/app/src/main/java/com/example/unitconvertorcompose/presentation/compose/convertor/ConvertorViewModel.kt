package com.example.unitconvertorcompose.presentation.compose.convertor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unitconvertorcompose.data.model.Conversion
import com.example.unitconvertorcompose.data.model.ConversionResult
import com.example.unitconvertorcompose.domain.repository.ConvertorRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ConvertorViewModel(val repositoryImpl: ConvertorRepositoryImpl) : ViewModel() {

    fun getConversions() = listOf<Conversion>(
        Conversion(1, "Pounds to Kilogram", "lbs", "kg", 0.453592),
        Conversion(2, "Kilogram To Pounds", "kg", "lbs", 2.204),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.0936),
        Conversion(5, "Miles to Kilometer", "mi", "km", 1.6093),
        Conversion(6, "Kilometer to Meter", "km", "mi", 0.6213)

    )

    fun getHistoryList(): Flow<List<ConversionResult>> {
        println("ConvertorViewModel getHistoryList")

        return repositoryImpl.getAllResult()
    }


    fun addResult(convertFrom: String, convertTo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            println("ConvertorViewModel addResult")
            repositoryImpl.insertResult(ConversionResult(0, convertFrom, convertTo))
        }
    }

    fun deleteResult(conversionResult: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            println("ConvertorViewModel deleteResult")
            repositoryImpl.deleteResult(conversionResult)
        }
    }

    fun deleteAllResults() {
        viewModelScope.launch(Dispatchers.IO) {
            println("ConvertorViewModel deleteAllResults")
            repositoryImpl.deleteAllResult()
        }
    }


}