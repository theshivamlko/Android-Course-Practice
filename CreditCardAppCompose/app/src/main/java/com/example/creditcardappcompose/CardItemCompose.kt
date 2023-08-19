package com.example.creditcardappcompose

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItemCompose(cardInfo: CardInfo) {

    Card(
        onClick = {

        },
        modifier = Modifier
            //   .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.Gray),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.abstract2),
                contentDescription = "Visa",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Box(modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()) {

                Text(
                    text = "Visa", fontSize = 30.sp,
                    modifier = Modifier.align(Alignment.TopEnd)
                )


                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    Text(text = cardInfo.cardNo, fontSize = 20.sp, letterSpacing = 1.1.sp)
                    Text(text = cardInfo.userName, fontSize = 18.sp)
                }
            }

        }
    }


}

@Composable
@Preview

fun Preview() {
    CardItemCompose(cardInfo = CardInfo("93920120320", "Shivam", "Visa", R.drawable.abstract1))
}