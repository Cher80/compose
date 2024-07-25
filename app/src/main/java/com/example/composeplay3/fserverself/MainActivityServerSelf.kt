package com.example.composeplay3.fserverself

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivityServerSelf : ComponentActivity() {
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
    ServerView(
        modifier = Modifier
            .wrapContentWidth().wrapContentHeight()
    )
}