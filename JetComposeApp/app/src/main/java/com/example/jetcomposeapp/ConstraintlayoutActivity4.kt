package com.example.jetcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.jetcomposeapp.ui.theme.JetComposeAppTheme

class ConstraintlayoutActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting3("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Column {
        ConstraintLayout {
            val (red, blue, yellow) = createRefs()

            Box(modifier = Modifier
                .background(Color.Red)
                .size(100.dp)
                .constrainAs(red) {

                    //  width= Dimension.matchParent
                    //  width= Dimension.fillToConstraints
                    height = Dimension.value(200.dp)

                    // TO center child
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                })

            Box(modifier = Modifier
                .background(Color.Yellow)
                .size(70.dp)
                .constrainAs(yellow) {
                    top.linkTo(red.bottom)
                })

            Box(modifier = Modifier
                .background(Color.Blue)
                .size(50.dp)
                .constrainAs(blue) {
                    start.linkTo(yellow.end)
                    start.linkTo(red.end)
                    top.linkTo(red.bottom)


                })

        }

        Divider(thickness = 2.dp)

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (cyan, black, green) = createRefs()

            Box(modifier = Modifier
                .size(60.dp)
                .background(Color.Cyan).constrainAs(cyan){})

            Box(modifier = Modifier
                .background(Color.Black)
                .size(60.dp).constrainAs(black){})

            Box(modifier = Modifier
                .background(Color.Green)
                .size(60.dp).constrainAs(green){})

            createHorizontalChain(cyan,black, green,chainStyle = ChainStyle.SpreadInside)

        }



    }


}

