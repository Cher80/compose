package com.example.composeplay3.fdraw

import android.R.attr.animation
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplay3.ui.theme.ComposePlay3Theme
import com.example.composeplay3.ui.theme.Pink80
import com.example.composeplay3.ui.theme.Purple40
import kotlinx.coroutines.delay


@Composable
fun DrawView(modifier: Modifier) {

//    val tick = remember {
//        mutableStateOf<Long>(0L)
//    }

    val angle = remember {
        Animatable(0f)
    }


    LaunchedEffect(key1 = true) {
        Log.d("gcompose", "LaunchedEffect ${System.currentTimeMillis()}")
        angle.animateTo(
            1f,
            animationSpec = infiniteRepeatable(
                animation = tween(700, easing =  FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
//        while (true) {
//            //in-order to get smooth transition we are updating rotation angle every 16ms
//            //1000ms -> 6 degree
//            //16ms -> 0.096
//            //delay(160)
//
//            //tick.value = tick.value + 1
//            angle.animateTo(
//                1f,
//                animationSpec = infiniteRepeatable(
//                    animation = tween(700, easing =  FastOutSlowInEasing),
//                    repeatMode = RepeatMode.Reverse
//                )
//            )
//        }
    }


    Log.d("gcompose", "angle=${angle.value}")
    Canvas(modifier = modifier) {
        //val brush: Brush = Brush.

        val colorRed: Color = Color.Red
        val colorGreen: Color = Color.Green
        val colorBlue: Color = Color.Blue
        val colorMagenta: Color = Color.Magenta

        drawRect(
            color = Pink80
        )

        translate(
            left = (size.width - 20.dp.toPx()) * angle.value,
            top = 0.dp.toPx()
        ) {
            drawRect(
                size = Size(20.dp.toPx(), 20.dp.toPx()),
                color = Purple40
            )
        }

        translate(
            left = 15.dp.toPx(),
            top = 15.dp.toPx()
        ) {
            drawCircle(
                radius = 15.dp.toPx(),
                color = colorRed,
                center = Offset.Zero
            )
        }

        translate(
            left = size.width - 15.dp.toPx(),
            top = 15.dp.toPx()
        ) {
            drawCircle(
                radius = 15.dp.toPx(),
                color = colorGreen,
                center = Offset.Zero
            )
        }

        translate(
            left = size.width - 15.dp.toPx(),
            top = size.height - 15.dp.toPx(),
        ) {
            drawCircle(
                radius = 15.dp.toPx(),
                color = colorBlue,
                center = Offset.Zero
            )
        }


        translate(
            left = 15.dp.toPx(),
            top = size.height - 15.dp.toPx(),
        ) {
            drawCircle(
                radius = 15.dp.toPx(),
                color = colorMagenta,
                center = Offset.Zero
            )
        }
    }

//    Text(
//        modifier = Modifier
//            .wrapContentWidth().wrapContentHeight(),
//        maxLines = 5,
//        text = "tick = ${tick.value}",
//        style = TextStyle(
//            color = Color(0xFFFFFFFF)
//        )
//    )
}

@Preview(showBackground = true)
@Composable
fun DrawViewPreview() {
    ComposePlay3Theme {
        DrawView(
            modifier = Modifier
                .size(200.dp, 200.dp)
        )
    }
}