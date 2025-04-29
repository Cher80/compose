package com.example.composeplay3.s002001_nav.ui.nav

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplay3.ui.theme.ComposePlay3Theme

@Composable
fun NavButtons(navButtonsState: NavButtonsState) {

    Log.d("gcompose", "NavButtons compose")
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.background(color = Color.LightGray)
    ) {
        Row() {
            Button(
                onClick = {
                    navButtonsState.gotoRoute("ScreenBaseMain")
                },
                modifier = Modifier
                    .padding(0.dp)
                    .wrapContentWidth()
                    .padding(0.dp)
            ) {
                Text(
                    text = "BaseMain",
                    style = TextStyle(
                        color = Color.Red
                    )
                )
            }

            Button(
                onClick = {
                    navButtonsState.gotoRoute("ScreenBasePayments")
                },
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                Text(
                    text = "BasePayments",
                    style = TextStyle(
                        color = Color.Red
                    )
                )
            }

            Button(
                onClick = {
                    navButtonsState.gotoRoute("ScreenBaseSettings")
                },
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                Text(
                    text = "BaseSettings",
                    style = TextStyle(
                        color = Color.Red
                    )
                )
            }
        }


        Button(
            onClick = {
                navButtonsState.gotoRoute("ScreenSecondProduct/xxx?startScreen=one")
            },
            modifier = Modifier
                .wrapContentWidth()
        ) {
            Text(
                text = "SecondProduct",
                style = TextStyle(
                    color = Color.Red
                )
            )
        }


        Row {
            Button(
                onClick = {
                    navButtonsState.changeTabBarVisibility(false)
                },
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                Text(
                    text = "Hide bottom",
                    style = TextStyle(
                        color = Color.Red
                    )
                )
            }

            Button(
                onClick = {
                    navButtonsState.changeTabBarVisibility(true)
                },
                modifier = Modifier
                    .wrapContentWidth()
            ) {
                Text(
                    text = "Show bottom",
                    style = TextStyle(
                        color = Color.Red
                    )
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ComposableNavPreview() {
    ComposePlay3Theme {
        NavButtons(
            navButtonsState = NavButtonsState.TEST
        )
    }
}