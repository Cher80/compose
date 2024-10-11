package com.example.composeplay3.ftabs.screens.secondproduct

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun ScreenSecondProduct(
    userId: String, startScreen: String,
    navController: NavController? = null
) {
    Log.d("mmeme", "ScreenBox userId=$userId")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF64965C), RoundedCornerShape(16.dp))
    ) {

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp),
            text = "ScreenSecondProduct $startScreen $userId",
            style = TextStyle(
                color = Color.Black
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenSecondProductPreview() {
    ComposePlay3Theme {
        ScreenSecondProduct("Android", "ios")
    }
}