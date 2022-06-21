package com.example.unitconverterapp

import androidx.compose.runtime.Composable
import com.example.unitconverterapp.top.ConversionMenu

@Composable
fun TopScreen(list: List<Conversion>) {
    ConversionMenu(list = list)
}