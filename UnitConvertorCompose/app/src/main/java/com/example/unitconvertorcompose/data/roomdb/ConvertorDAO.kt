package com.example.unitconvertorcompose.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.unitconvertorcompose.data.model.ConversionResult
import kotlinx.coroutines.flow.Flow


@Dao
interface ConvertorDAO {

    @Insert
    suspend fun insertConversion(conversion: ConversionResult)

    @Query("SELECT * FROM conversions")
    fun getAllConversion(): Flow<List<ConversionResult>>

    @Delete
    suspend fun deleteConversion(conversion: ConversionResult)

    @Query("DELETE FROM conversions")
    suspend fun deleteAllConversion()


}