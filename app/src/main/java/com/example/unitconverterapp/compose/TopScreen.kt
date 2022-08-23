package com.example.unitconverterapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.unitconverterapp.data.Conversion
import com.example.unitconverterapp.histroy.ResultBlock
import com.example.unitconverterapp.compose.top.ConversionMenu
import com.example.unitconverterapp.compose.top.InputBlock
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(list: List<Conversion>) {
    val selectedConversion: MutableState<Conversion?> = remember{ mutableStateOf(null)}
    val inputText: MutableState<String> = remember {mutableStateOf("")}
    val typedValue = remember {mutableStateOf("0.0")}
    ConversionMenu(list = list) {
        selectedConversion.value = it
    }

    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText) { input ->
            Log.i("MYTAG", "User Typed $input")
            typedValue.value = input
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
        ResultBlock(message1 = message1, message2 = message2)
    }
}