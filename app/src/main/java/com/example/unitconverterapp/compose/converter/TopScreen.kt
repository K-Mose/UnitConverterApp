package com.example.unitconverterapp.compose.converter

import android.util.Log
import androidx.compose.runtime.*
import com.example.unitconverterapp.data.Conversion
import com.example.unitconverterapp.compose.converter.top.ResultBlock
import com.example.unitconverterapp.compose.converter.top.ConversionMenu
import com.example.unitconverterapp.compose.converter.top.InputBlock
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typedValue: MutableState<String>,
    save: (String, String) -> Unit
) {

    var toSave by remember {
        mutableStateOf(false)
    }

    ConversionMenu(list = list) {
        selectedConversion.value = it
        typedValue.value = "0.0"
    }

    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText) { input ->
            Log.i("MYTAG", "User Typed $input")
            typedValue.value = input
            toSave = true
        }
    }

    if (typedValue.value != "0.0") {
        val input = typedValue.value.toDouble()
        val multiply = selectedConversion.value!!.multiplyBy
        val result = DecimalFormat("#.####")
            .run {
                RoundingMode.DOWN
                format(input*multiply)
            }
        val message1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is Equal to"
        val message2 = "$result ${selectedConversion.value!!.convertTo}"
        if (toSave) {
            save(message1, message2)
            toSave = false
        }
        ResultBlock(message1 = message1, message2 = message2)
    }
}