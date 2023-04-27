package com.example.composeplay3

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenRow(userId: String, startScreen: String, navController: NavController? = null) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(Color(0xff00ffFF), RoundedCornerShape(16.dp))
    ) {
        val composables = createRefs()
        val s1 = composables.component1()
        val s2 = composables.component2()
        Log.d("dgty", "s1=$s1 s2=$s2")

        Text(
            modifier = Modifier
                .wrapContentWidth(),
            text = "Row $startScreen $userId",
            style = TextStyle(
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .clickable { navController?.navigate(Navs.Box.screenRoute + "/xxx?startScreen=" + startScreen) }
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
                .clickable { navController?.navigate(Navs.Constraint.screenRoute + "/xxx?startScreen=" + startScreen) }
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
                .padding(16.dp),
            text = "Cons",
            style = TextStyle(
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .clickable { navController?.navigate(Navs.Row.screenRoute + "/xxx?startScreen=" + startScreen) }
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
                .padding(16.dp),
            text = "Row",
            style = TextStyle(
                color = Color.Black
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .clickable { navController?.navigate(Navs.Col.screenRoute + "/xxx?startScreen=" + startScreen) }
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
                .padding(16.dp),
            text = "Col",
            style = TextStyle(
                color = Color.Black
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenRowPreview() {
    ComposePlay3Theme {
        ScreenConstraint("Android", "ios")
    }
}