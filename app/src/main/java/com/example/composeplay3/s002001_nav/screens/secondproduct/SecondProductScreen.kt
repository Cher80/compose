package com.example.composeplay3.s002001_nav.screens.secondproduct

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplay3.s002001_nav.ui.nav.NavButtons
import com.example.composeplay3.s002001_nav.ui.nav.NavButtonsState
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun ScreenSecondProduct(
    secondProductState: SecondProductState?,
    navButtonsState: NavButtonsState
) {

    if (secondProductState == null) return
    val products = remember {
        mutableIntStateOf(1)
    }

    Log.d(
        "gcompose",
        "ScreenSecondProduct products=${products.intValue} productsCount=${secondProductState?.productsCount} userId=${secondProductState.userId} startScreen=${secondProductState?.startScreen}"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF64965C), RoundedCornerShape(16.dp))
    ) {

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp),
            text = "ScreenSecondProduct productsRemember=${products.intValue} productsCountViewmodel=${secondProductState.productsCount} userId=${secondProductState.userId} startScreen=${secondProductState.startScreen}",
            style = TextStyle(
                color = Color.Black
            )
        )

        Button(
            onClick = {
                products.intValue++
            }) {
            Text("Plus products")
        }

        Button(
            onClick = secondProductState.productsCountPlus
        ) {
            Text("Plus products viewmodel")
        }

        NavButtons(
            navButtonsState = navButtonsState
        )
    }


}

@Preview(showBackground = true)
@Composable
fun ScreenSecondProductPreview() {
    ComposePlay3Theme {
        ScreenSecondProduct(
            secondProductState = SecondProductState.TEST,
            navButtonsState = NavButtonsState.TEST
        )
    }
}