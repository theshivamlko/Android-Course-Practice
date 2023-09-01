package com.example.unitconvertorcompose.presentation.compose.convertor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.unitconvertorcompose.data.model.Conversion


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionMenu(
    list: List<Conversion>, modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit,
) {

    var displayText by remember { mutableStateOf("Select option") }
    // to assign same width of TextField
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var isExpanded by remember { mutableStateOf(false) }

    val icon = if (isExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown

    }
    Text(text = "Conversion Type")


    OutlinedTextField(
        value = displayText,
        onValueChange = { displayText = it },

        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            //  .background(Color.Blue)
            .fillMaxWidth()
            .onGloballyPositioned { cord ->
                textFieldSize = cord.size.toSize()
            },

        trailingIcon = {
            Icon(imageVector = icon, contentDescription = null,
                modifier
                    .padding(10.dp)
                    .clickable {
                        isExpanded = !isExpanded
                        println(isExpanded)
                    })


        },
        readOnly = true
    )

    Column {
        DropdownMenu(
            expanded = isExpanded, onDismissRequest = {
                isExpanded = false
            }, modifier = modifier

                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            //   .fillMaxWidth()
            //       .background(Color.Green)
        ) {
            list.forEach {
                DropdownMenuItem(text = {
                    Text(text = it.description, fontSize = 18.sp)
                },
                    onClick = {
                        displayText = it.description
                        isExpanded = false
                        convert.invoke(it)
                    })
            }

        }
    }


}