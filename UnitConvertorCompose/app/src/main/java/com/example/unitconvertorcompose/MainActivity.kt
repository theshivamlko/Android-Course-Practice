package com.example.unitconvertorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.unitconvertorcompose.presentation.ConvertorApp
import com.example.unitconvertorcompose.presentation.compose.BaseScreen
import com.example.unitconvertorcompose.presentation.compose.convertor.ConvertorViewModelFactory
import com.example.unitconvertorcompose.ui.theme.UnitConvertorComposeTheme
import com.example.unitconvertorcompose.utils.CreateInstance
import io.flutter.embedding.android.FlutterActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = CreateInstance.createFactoryInstances(this)

        setContent {
            val context = LocalContext.current;
            UnitConvertorComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Column {
                        BaseScreen(
                            factory = factory,
                            modifier = Modifier.background(Color.White)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)

                        ) {
                            Button(onClick = {

                                context.startActivity(
                                    FlutterActivity.withCachedEngine(ConvertorApp.FLUTTER_ENGINE_NAME)
                                        .build(context)
                                )

                            }) {
                                Text(text = "Open Flutter Page")
                            }
                        }
                    }


                }
            }
        }
    }
}

