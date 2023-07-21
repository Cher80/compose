package com.example.composeplay3.fserverself

import android.os.Looper
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeplay3.ui.theme.ComposePlay3Theme

@Composable
fun ServerView(modifier: Modifier) {
    val jstate: MutableState<String> = remember {
        mutableStateOf("xxx sadsad asdasdas as dasd asd asd asd ")
    }
//    val jstate: MutableState<String> = mutableStateOf("xxx sadsad asdasdas as dasd asd asd asd ")
    android.os.Handler(Looper.getMainLooper()).postDelayed({
        jstate.value = "xxx2 w ${System.currentTimeMillis()}"
    }, 3000)

    Log.d(
        "gcompose", "BannerView jstate = $jstate "
    )

    Box(
        modifier = modifier
            .background(Color(0xFF6200FF))
            .clickable(onClick = {})
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth().wrapContentHeight(),
            maxLines = 5,
            text = "A'm bannerId jstate = ${jstate.value}",
            style = TextStyle(
                color = Color(0xFFFFFFFF)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ServerViewPreview() {
    ComposePlay3Theme {
        ServerView(
            modifier = Modifier
                .wrapContentHeight().wrapContentWidth()
        )
    }
}