package com.example.composeplay3.s002001_nav.screens.basepayments

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeplay3.s002001_nav.navigation.NavScreenContext
import com.example.composeplay3.s002001_nav.navigation.getTabBarPaddingWithImeState
import com.example.composeplay3.s002001_nav.ui.nav.NavButtons
import com.example.composeplay3.s002001_nav.ui.nav.NavButtonsState
import com.example.composeplay3.ui.theme.ComposePlay3Theme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScreenBasePayments(
    navScreenContext: NavScreenContext,
    navButtonsState: NavButtonsState
) {
    Log.d(
        "gcompose",
        "ScreenBasePayments navScreenContext.tabBarVisible=${navScreenContext.tabBarVisible} WindowInsets.isImeVisible=${WindowInsets.isImeVisible}"
    )
    //if (navScreenContext.tabBarVisible && !WindowInsets.isImeVisible) navScreenContext.tabBarHeight
    //val bottomPadding: Dp by animateDpAsState(if (navScreenContext.tabBarVisible && !WindowInsets.isImeVisible) navScreenContext.tabBarHeight else 0.dp)

    var textIn by remember { mutableStateOf("Hello") }
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .systemBarsPadding()
            .background(Color(0xFF00CCFF), RoundedCornerShape(24.dp))
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.TopCenter),
            text = "ScreenBasePayments",
            style = TextStyle(
                color = Color.Red
            )
        )

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(bottom = navScreenContext.getTabBarPaddingWithImeState().value)
                .statusBarsPadding()
                .navigationBarsPadding()
                .displayCutoutPadding()
                .imePadding()
                .verticalScroll(scrollState)
        ) {
            NavButtons(
                navButtonsState = navButtonsState
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "01",
                style = TextStyle(
                    color = Color.Red
                )
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "02",
                style = TextStyle(
                    color = Color.Red
                )
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "03",
                style = TextStyle(
                    color = Color.Red
                )
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "04",
                style = TextStyle(
                    color = Color.Red
                )
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "05",
                style = TextStyle(
                    color = Color.Red
                )
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "06",
                style = TextStyle(
                    color = Color.Red
                )
            )

            TextField(
                value = textIn,
                onValueChange = { textIn = it },
                label = { Text("Label") }
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "07",
                style = TextStyle(
                    color = Color.Red
                )
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "08",
                style = TextStyle(
                    color = Color.Red
                )
            )

            Text(
                modifier = Modifier.height(100.dp),
                text = "09",
                style = TextStyle(
                    color = Color.Red
                )
            )


        }

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.BottomCenter),
            text = "ScreenBasePaymentsEnd",
            style = TextStyle(
                color = Color.Red
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenBasePaymentsPreview() {
    ComposePlay3Theme {
        ScreenBasePayments(
            navScreenContext = NavScreenContext.TEST,
            navButtonsState = NavButtonsState.TEST
        )
    }
}