package com.example.composeplay3.s002001_nav.screens.basesettings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplay3.s002001_nav.ui.nav.NavButtons
import com.example.composeplay3.s002001_nav.ui.nav.NavButtonsState
import com.example.composeplay3.ui.theme.ComposePlay3Theme

@Composable
fun ScreenBaseSettings(
    navButtonsState: NavButtonsState
) {
    Log.d("gcompose", "ScreenBaseSettings")

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .systemBarsPadding()
            .background(Color(0xFFD0B9D1), RoundedCornerShape(16.dp))
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth().align(Alignment.TopCenter),
            text = "ScreenBaseSettings",
            style = TextStyle(
                color = Color.Red
            )
        )

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.padding(top = 30.dp).verticalScroll(scrollState)
        ) {
            NavButtons(
                navButtonsState = navButtonsState
            )
        }

        Text(
            modifier = Modifier
                .wrapContentWidth().align(Alignment.BottomCenter),
            text = "ScreenBaseSettingsEnd",
            style = TextStyle(
                color = Color.Red
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenBaseSettingsPreview() {
    ComposePlay3Theme {
        ScreenBaseSettings(
            navButtonsState = NavButtonsState.TEST
        )
    }
}