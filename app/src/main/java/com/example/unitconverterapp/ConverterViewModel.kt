package com.example.unitconverterapp

import androidx.lifecycle.ViewModel
import com.example.unitconverterapp.data.Conversion

class ConverterViewModel: ViewModel() {

    // 변환 리스트
    fun getConversions() = listOf(
        Conversion(1, description = "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, description = "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, description = "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, description = "Meters to Yards", "m", "yd", 1.09361),
        Conversion(5, description = "Miles to Kilometers", "mi", "km", 1.60934),
        Conversion(6, description = "Kilometers to Miles", "km", "mi", 0.621371)
    )

}