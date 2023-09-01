package com.example.unitconvertorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.unitconvertorcompose.presentation.compose.BaseScreen
import com.example.unitconvertorcompose.presentation.compose.convertor.ConvertorViewModelFactory
import com.example.unitconvertorcompose.ui.theme.UnitConvertorComposeTheme
import com.example.unitconvertorcompose.utils.CreateInstance

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       val factory = CreateInstance.createFactoryInstances(this)

        setContent {
            UnitConvertorComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    BaseScreen(
                        factory = factory,
                        modifier = Modifier.background(Color.White)
                    )

                }
            }
        }
    }
}

