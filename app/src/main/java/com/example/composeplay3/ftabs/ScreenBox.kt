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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun ScreenBox(userId: String, startScreen: String, navController: NavController? = null) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(Color(0xFF9AD690), RoundedCornerShape(16.dp))
    ) {

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.TopCenter)
                .padding(16.dp)
                .padding(16.dp),
            text = "A'm Box $startScreen $userId",
            style = TextStyle(
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.TopStart)
                .padding(16.dp)
                .clickable {
                    ii++
                    navController?.navigate(Navs.Box.screenRoute + "/xxx?startScreen=" + startScreen)
                }
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
                .padding(16.dp),
            text = "Box",
            style = TextStyle(
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    ii++
                    navController?.navigate(Navs.Constraint.screenRoute + "/xxx?startScreen=" + startScreen)
                }
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
                .padding(16.dp),
            text = "cons",
            style = TextStyle(
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterStart)
                .padding(16.dp)
                .clickable {
                    ii++
                    navController?.navigate(Navs.Row.screenRoute + "/xxx?startScreen=" + startScreen)
                }
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
                .padding(16.dp),
            text = "row",
            style = TextStyle(
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterEnd)
                .padding(16.dp)
                .clickable {
                    ii++
                    navController?.navigate(Navs.Col.screenRoute + "/xxx?startScreen=" + startScreen)
                }
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
                .padding(16.dp),
            text = "col",
            style = TextStyle(
                color = Color.Black
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenBoxPreview() {
    ComposePlay3Theme {
        ScreenBox("Android", "ios")
    }
}