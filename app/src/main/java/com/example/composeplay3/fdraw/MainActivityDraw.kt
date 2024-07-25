package com.example.composeplay3.fdraw

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivityDraw : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SelfUpdateContent()
        }
    }
}

@Composable
fun SelfUpdateContent() {
    Log.d("gcompose", "SelfUpdateContent")
    DrawView(
        modifier = Modifier
            .size(200.dp, 200.dp)
    )
}