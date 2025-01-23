package com.example.composeplay3.s002001_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeplay3.ui.theme.ComposePlay3Theme

@Composable
fun SheetOne(userId: String, startScreen: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff00ffFF))
    ) {

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp)
                .padding(16.dp),
            text = "A'm sheet $startScreen $userId",
            style = TextStyle(
                color = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SheetOnePreview() {
    ComposePlay3Theme {
        SheetOne("Android", "ios")
    }
}