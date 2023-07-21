package com.example.composeplay3.fbannerwidth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplay3.ui.theme.ComposePlay3Theme

@Composable
fun BannerView(state: BannerState, modifier: Modifier) {

    val rememberState = remember {
        mutableStateOf(state)
    }

    //rememberState.value = bannerViewState
    //Log.d("gcompose", "BannerViewState rememberState = $rememberState")

    Box(
        modifier = modifier
            .background(Color(0xFF6200FF))
            .clickable(onClick = state.onClick)
    ) {
        Text(
            modifier = Modifier.width(100.dp),
            text = "A'm BannerViewState state=${state.bannerId} rememberState=${rememberState.value.bannerId}",
            style = TextStyle(
                color = Color(0xFFFFFFFF)
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BannerViewPreview() {
    ComposePlay3Theme {
        BannerView(
            state = BannerState(
                bannerId = "Android",
                onClick = {}
            ),
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        )
    }
}