package com.example.composeplay3.s002001_nav.screens.basesettings

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeplay3.s002001_nav.navigation.NavScreenContext
import com.example.composeplay3.s002001_nav.ui.nav.NavButtons
import com.example.composeplay3.s002001_nav.ui.nav.NavButtonsState
import com.example.composeplay3.ui.theme.ComposePlay3Theme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScreenBaseSettings(
    navScreenContext: NavScreenContext,
    navButtonsState: NavButtonsState
) {

    val statusHeight = with(LocalDensity.current){ WindowInsets.statusBars.getTop(LocalDensity.current).toDp()}
    val navHeight = with(LocalDensity.current){ WindowInsets.navigationBars.getBottom(LocalDensity.current).toDp()}
    Log.d("gcompose", "ScreenBaseSettings statusHeight=$statusHeight navHeight=$navHeight")

    val topContentPadding by animateDpAsState(statusHeight)
    val bottomContentPadding by animateDpAsState(if (navScreenContext.tabBarVisible && !WindowInsets.isImeVisible) navScreenContext.tabBarHeight + navHeight else navHeight)


    Box(
        modifier = Modifier
            .fillMaxSize()
//            .systemBarsPadding()
            .background(Color(0xFFD0B9D1), RoundedCornerShape(24.dp))
    ) {
//        contentPadding = with(LocalDensity.current) {
//            PaddingValues(
//                top = WindowInsets.statusBars.getTop(this).toDp(),
//                bottom = WindowInsets.navigationBars.getBottom(this).toDp()
//            )
//        }
        val data = (0..20).map { "kk_$it" }.toList()
        LazyColumn(
            contentPadding = PaddingValues(
                top = topContentPadding,
                bottom = bottomContentPadding
            )
        ) {
            items(data.size) { index ->
                Text(
                    modifier = Modifier.height(100.dp)
                        .fillMaxWidth()
                        .border(width = 2.dp, color = Red, shape = RectangleShape),
                    text = data[index],
                    fontSize = 30.sp,
                    style = TextStyle(
                        color = Color.Red
                    )
                )

            }
        }
        NavButtons(
            navButtonsState = navButtonsState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenBaseSettingsPreview() {
    ComposePlay3Theme {
        ScreenBaseSettings(
            navScreenContext = NavScreenContext.TEST,
            navButtonsState = NavButtonsState.TEST
        )
    }
}