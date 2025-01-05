package com.example.composeplay3.s001001_mutable_state

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun SimpleView(modifier: Modifier, simpleViewState: SimpleViewState) {
    //var expand = true
    val expand = remember { mutableStateOf(false) }
    Log.d("gcompose", "SimpleView simpleViewState=$simpleViewState expand=$expand")
    Column(
        modifier = modifier.background(
            color = Color.Magenta
        ),
    ) {
        Text(text = simpleViewState?.name ?: "")
        Text(
            modifier = Modifier.padding(20.dp),
            text = simpleViewState?.count.toString()
        )
        Button(onClick = {
            expand.value = !expand.value
        }) {
            Text(text = "Expand")
        }
        if (expand.value) {
            Text(text = "Im expanded!!")
        }

        Button(onClick = simpleViewState.pokeButtonClick) {
            Text(text = simpleViewState.pokeButtonText)
        }
        Text(text = "Im poked ${simpleViewState.poked}")

        Button(onClick = simpleViewState.addUserClick) {
            Text(text = "Add user")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawViewPreview() {
    ComposePlay3Theme {
        SimpleView(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            simpleViewState = SimpleViewState.TEST
        )
    }
}