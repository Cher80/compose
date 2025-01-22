package com.example.composeplay3.s002001_nav.screens.secondproduct

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.composeplay3.R
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun ScreenSecondHeart(
    modifier: Modifier,
    id: Int,
    finished: (Int) -> Unit
) {

    val expanded = remember { mutableStateOf(false) }
    val finishedListenerRemember: (Dp) -> Unit = remember{
        { dp ->
            Log.d("gcompose", "ScreenSecondHeart finishedHeart finishedListener id=$id dp=$dp")
            finished(id)
        }
    }

    val offsetState = animateDpAsState(
        animationSpec = tween(1700, easing =  FastOutSlowInEasing),
        targetValue = if (expanded.value) (-200).dp else 0.dp,
        label = "xxx $id",
        finishedListener = finishedListenerRemember
    )

    LaunchedEffect(key1 = true) {
        Log.d("gcompose", "ScreenSecondHeart LaunchedEffect id=$id")
        expanded.value = true
    }

//    Log.d("gcompose", "ScreenSecondHeart id=$id expanded=$expanded offsetState=${offsetState.value}")

    Box(
        modifier = modifier
            .offset {
                IntOffset(0.dp.roundToPx(),offsetState.value.roundToPx())
            }.clickable { expanded.value = !expanded.value },
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_heart_broken_24),
            colorFilter = ColorFilter.tint(Color.Red),
            contentDescription = "xx",
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Center)
            ,
            text = "$id",
            style = TextStyle(
                color = Color.Black
            )
        )
    }

}



@Preview(showBackground = true)
@Composable
fun ScreenSecondHeartPreview() {
    ComposePlay3Theme {
        ScreenSecondHeart(
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            id = 0,
            finished = {}
        )
    }
}