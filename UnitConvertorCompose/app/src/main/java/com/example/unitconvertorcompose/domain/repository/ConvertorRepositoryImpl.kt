package com.example.unitconvertorcompose.domain.repository

import com.example.unitconvertorcompose.data.model.ConversionResult
import com.example.unitconvertorcompose.data.roomdb.ConvertorDAO
import kotlinx.coroutines.flow.Flow

class ConvertorRepositoryImpl (val convertorDAO: ConvertorDAO):IConvertorRepository {
    override suspend fun insertResult(result: ConversionResult) {
        convertorDAO.insertConversion(result)
    }

    override suspend fun deleteResult(result: ConversionResult) {
        convertorDAO.deleteConversion(result)
    }

    override suspend fun deleteAllResult() {
        convertorDAO.deleteAllConversion()
    }

    override fun getAllResult(): Flow<List<ConversionResult>> {
      return  convertorDAO.getAllConversion()
    }

    
}