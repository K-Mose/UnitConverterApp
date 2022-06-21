package com.example.unitconverterapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.unitconverterapp.top.ConversionMenu
import com.example.unitconverterapp.top.InputBlock

@Composable
fun TopScreen(list: List<Conversion>) {
    val selectedConversion: MutableState<Conversion?> = remember{ mutableStateOf(null)}
    val inputText: MutableState<String> = remember {mutableStateOf("")}
    ConversionMenu(list = list) {
        selectedConversion.value = it
    }

    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText)
    }
}