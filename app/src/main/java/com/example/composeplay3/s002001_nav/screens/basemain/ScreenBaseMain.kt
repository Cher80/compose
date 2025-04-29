package com.example.composeplay3.s002001_nav.screens.basemain

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeplay3.s002001_nav.navigation.NavScreenContext
import com.example.composeplay3.s002001_nav.ui.nav.NavButtons
import com.example.composeplay3.s002001_nav.ui.nav.NavButtonsState
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun ScreenBaseMain(navScreenContext: NavScreenContext, navButtonsState: NavButtonsState) {

    Log.d("gcompose", "ScreenBaseMain navButtonsState=$navButtonsState")
    val bottomPadding: Dp by animateDpAsState(if (navScreenContext.tabBarVisible) navScreenContext.tabBarHeight else 0.dp)
    Box(
        modifier = Modifier
//            .fillMaxSize()
//            .systemBarsPadding()
            .background(Color(0xFF8F98BB), RoundedCornerShape(24.dp))
    ) {

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.TopCenter),
            text = "ScreenBaseMain",
            style = TextStyle(
                color = Color.Red
            )
        )

        Box(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    //.padding(top = 30.dp)
//                .systemBarsPadding()
                    .verticalScroll(scrollState)
            ) {
                NavButtons(
                    navButtonsState = navButtonsState
                )
            }

            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(150.dp)
                    .background(color = Color.Blue)
                    .align(Alignment.BottomEnd)
            ) {
            }
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(150.dp)
                    .background(color = Color.Blue)
                    .align(Alignment.TopEnd)
            ) {
            }

//            AnimatedVisibility(
//                    visible = true,
//                    enter = slideInVertically(
//                        initialOffsetY = {
//                            it
//                        }
//                    ),
//                    exit = slideOutVertically(
//                        targetOffsetY = {
//                            it
//                        }
//                    ),
//                    modifier = Modifier
//                ) {
//                Spacer(modifier = Modifier.height(70.dp))
//            }


        }

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.BottomCenter),
            text = "ScreenBaseMainEnd",
            style = TextStyle(
                color = Color.Red
            )
        )

        Box(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .background(color = Color.Cyan)
                .align(Alignment.BottomEnd)
        ) {
        }
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .background(color = Color.Cyan)
                .align(Alignment.TopEnd)
        ) {
        }


//        val mod = if (navButtonsState.tabBarActuallyVisible) {
//            Modifier
//                .fillMaxSize()
//        } else {
//            Modifier
//                .systemBarsPadding()
//                .fillMaxSize()
//        }


    }
}


@Preview(showBackground = true)
@Composable
fun ScreenBaseMainPreview() {
    ComposePlay3Theme {
        ScreenBaseMain(
            navScreenContext = NavScreenContext.TEST,
            navButtonsState = NavButtonsState.TEST
        )
    }
}