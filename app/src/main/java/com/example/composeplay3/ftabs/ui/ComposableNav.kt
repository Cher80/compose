package com.example.composeplay3.ftabs.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.composeplay3.ftabs.bottomNavigatonClick
import com.example.composeplay3.ftabs.navigation.Navs
import com.example.composeplay3.ui.theme.ComposePlay3Theme

@Composable
fun ComposableNav(navController: NavController? = null) {

    val scrollState = rememberScrollState()
    Column() {
        Row {
            Button(
                onClick = {
                    navController?.bottomNavigatonClick(Navs.ScreenBaseMain.screenRoute)
                },
                modifier = Modifier
                    .wrapContentWidth()
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
                    navController?.bottomNavigatonClick(Navs.ScreenBasePayments.screenRoute)
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
                    navController?.bottomNavigatonClick(Navs.ScreenBaseSettings.screenRoute)
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
            onClick = { navController?.navigate(Navs.ScreenSecondProduct.screenRoute + "/xxx?startScreen=one") },
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

        Button(
            onClick = { navController?.navigate(Navs.Row.screenRoute + "/xxx?startScreen=one") },
            modifier = Modifier
                .wrapContentWidth()
        ) {
            Text(
                text = "Row",
                style = TextStyle(
                    color = Color.Red
                )
            )
        }


        Row {
            Button(
                onClick = {
                    navController?.bottomNavigatonClick(Navs.ScreenBaseMain.screenRoute)
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
                    navController?.bottomNavigatonClick(Navs.ScreenBasePayments.screenRoute)
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
        ComposableNav()
    }
}