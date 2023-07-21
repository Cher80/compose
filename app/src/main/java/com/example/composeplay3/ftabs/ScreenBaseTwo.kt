package com.example.composeplay3.ftabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenBaseTwo(navController: NavController? = null) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(Color(0xFF8800FF), RoundedCornerShape(16.dp))
    ) {

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp)
                .clickable { navController?.navigate(Navs.Constraint.screenRoute + "/xxx?startScreen=two") }
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
                .padding(16.dp),
            text = "ScreenBaseTwo",
            style = TextStyle(
                color = Color.Red
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenBaseTwoPreview() {
    ComposePlay3Theme {
        ScreenBaseTwo()
    }
}