package com.example.composeplay3.s001001_mutable_state

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun UsersView(modifier: Modifier, usersViewState: UsersViewState) {
    Log.d("gcompose", "UsersView usersViewState=$usersViewState")

    Row (
        modifier = modifier.background(
            color = Color.LightGray
        ),
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = usersViewState.title.title
        )

        for (i in 0 until  usersViewState.users.size) {
            val user = usersViewState.users[i]
            Text(
                modifier = Modifier.padding(20.dp),
                text = user
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersViewPreview() {
    ComposePlay3Theme {
        UsersView(
            modifier = Modifier
                .size(200.dp, 200.dp),
            usersViewState = UsersViewState.TEST
        )
    }
}