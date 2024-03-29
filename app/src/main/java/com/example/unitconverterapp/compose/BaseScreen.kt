package com.example.unitconverterapp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitconverterapp.ConverterViewModel
import com.example.unitconverterapp.ConverterViewModelFactory
import com.example.unitconverterapp.compose.history.HistoryScreen
import com.example.unitconverterapp.compose.converter.TopScreen

@Composable
fun BaseScreen(
    factory: ConverterViewModelFactory,
    modifier: Modifier = Modifier,
    converterViewModel: ConverterViewModel = viewModel(factory = factory) // ViewModel Screen-level에서 주입 : https://developer.android.com/jetpack/compose/libraries#viewmodel
) {
    val list = converterViewModel.getConversions()
    val historyList = converterViewModel.resultList.collectAsState(initial = emptyList())


    Column(
        modifier = modifier.padding(30.dp)
    ) {
        // BaseScreen 내에서 Top과 History Screen을 나눠서 불러옴
        TopScreen(
            list,
            converterViewModel.selectedConversion,
            converterViewModel.inputText,
            converterViewModel.typedValue
        ) { message1, message2 ->
            converterViewModel.addResult(message1, message2)
        }
        Spacer(modifier = modifier.height(20.dp))
        HistoryScreen(historyList, { item ->
                converterViewModel.removeResult(item)
            },
            {
                converterViewModel.clearAll()
            }
        )
    }
}