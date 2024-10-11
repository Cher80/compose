package com.example.composeplay3.ftabs.screens.basemain

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeplay3.ftabs.navigation.AppNavigationController
import com.example.composeplay3.ftabs.ui.ComposableNav
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun ScreenBaseMain(
    navController: NavController? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Color(0xFF8F98BB), RoundedCornerShape(16.dp))
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth().align(Alignment.TopCenter),
            text = "ScreenBaseMain",
            style = TextStyle(
                color = Color.Red
            )
        )

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.padding(top = 30.dp).verticalScroll(scrollState)
        ) {
            ComposableNav(
                navController = navController
            )
        }

        Text(
            modifier = Modifier
                .wrapContentWidth().align(Alignment.BottomCenter),
            text = "ScreenBaseMainEnd",
            style = TextStyle(
                color = Color.Red
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenBaseMainPreview() {
    ComposePlay3Theme {
        ScreenBaseMain()
    }
}