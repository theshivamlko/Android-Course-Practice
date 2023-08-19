package com.example.creditcardappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.creditcardappcompose.ui.theme.CreditCardAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreditCardAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CardsListCompose("Android")
                }
            }
        }
    }
}

@Composable
fun CardsListCompose(name: String, modifier: Modifier = Modifier) {

    var list = listOf(
        CardInfo("93920120320", "Shivam", "Visa", R.drawable.abstract1),
        CardInfo("93920120320", "Shivam", "Visa", R.drawable.abstract1),
        CardInfo("93920120320", "Shivam", "Visa", R.drawable.abstract1),
        CardInfo("93920120320", "Shivam", "Visa", R.drawable.abstract1),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(list.size) {
            CardItemCompose(list.get(it))
        }
    }


}

