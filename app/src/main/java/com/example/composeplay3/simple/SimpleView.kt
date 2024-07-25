package com.example.composeplay3.simple

import android.R.attr.animation
import android.R.attr.backdropColor
import android.util.Log
import android.util.MutableBoolean
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
fun SimpleView(modifier: Modifier, simpleData: SimpleData) {
    Log.d("gcompose", "SimpleView simpleData=$simpleData")
    //var expand = true
    val expand = remember { mutableStateOf(false) }
    Column(
        modifier = modifier.background(
            color = Color.Magenta
        ),
    ) {
        Text(text = simpleData.name)
        Text(
            modifier = Modifier.padding(20.dp),
            text = simpleData.count.toString()
        )
        Button(onClick = {
            expand.value = !expand.value
        }) {
            Text(text = "Expand")
        }
        if (expand.value) {
            Text(text = "Im expanded!!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawViewPreview() {
    ComposePlay3Theme {
        SimpleView(
            modifier = Modifier
                .size(200.dp, 200.dp),
            simpleData = SimpleData(
                name = "Ann",
                count = 22
            )
        )
    }
}