package com.example.unitconvertorcompose.domain.repository

import com.example.unitconvertorcompose.data.model.ConversionResult
import kotlinx.coroutines.flow.Flow

interface IConvertorRepository {

    suspend fun insertResult(result: ConversionResult)
    suspend fun deleteResult(result: ConversionResult)
    suspend fun deleteAllResult()
    fun getAllResult(): Flow<List<ConversionResult>>
}