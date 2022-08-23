package com.example.unitconverterapp.compose.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.unitconverterapp.data.Conversion

@Composable
fun ConversionMenu(
    list: List<Conversion>,
    modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit
) {
    var displayText by remember {mutableStateOf("Select the Conversion Type")}
    var textFiledSize by remember { mutableStateOf(Size.Zero)} // To assign the dropdown then same width as TextField
    var expanded by remember { mutableStateOf(false)}

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column {
        OutlinedTextField(
            value = displayText,
            onValueChange = {displayText = it},
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { cordinates ->
                    textFiledSize = cordinates.size.toSize()
                },
            readOnly = true,
            label = { Text(text = "Conversion Type")},
            trailingIcon = {
                Icon(icon, contentDescription = "icon",
                modifier.clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current){textFiledSize.width.toDp()})
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(onClick = {
                    displayText = conversion.description
                    expanded = false
                    convert(conversion)
                }) {
                    Text(text = conversion.description,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
