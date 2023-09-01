package com.example.unitconvertorcompose.presentation

import androidx.lifecycle.ViewModel
import com.example.unitconvertorcompose.data.model.Conversion

class ConvertorViewModel : ViewModel() {

    fun getConversions() = listOf<Conversion>(
        Conversion(1, "Pounds to Kilogram", "lbs", "kg", 0.453592),
        Conversion(2, "Kilogram To Pounds", "kg", "lbs", 2.204),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.0936),
        Conversion(5, "Miles to Kilometer", "mi", "km", 1.6093),
        Conversion(6, "Kilometer to Meter", "km", "mi", 0.6213)

    )
}