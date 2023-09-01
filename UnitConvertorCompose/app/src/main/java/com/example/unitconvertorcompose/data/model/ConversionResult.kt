package com.example.unitconvertorcompose.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unitconvertorcompose.data.roomdb.ConvertorDAO
import com.example.unitconvertorcompose.domain.repository.IConvertorRepository
import kotlinx.coroutines.flow.Flow


@Entity(tableName = "conversions")
data class ConversionResult(
    @PrimaryKey(autoGenerate = true)
    val id:Int ,
    val convertFrom:String,
    val convertTo:String,
    )