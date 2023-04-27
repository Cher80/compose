package com.example.composeplay3

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.composeplay3.ui.theme.ComposePlay3Theme


@Composable
fun ScreenConstraint(userId: String, startScreen: String, navController: NavController? = null) {
    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(Color(0xff00ffFF), RoundedCornerShape(16.dp))
    ) {
        val composables = createRefs()
        val s1 = composables.component1()
        val s2 = composables.component2()
        val s3 = composables.component3()
        val s4 = composables.component4()
        val s5 = composables.component5()
        Log.d("dgty", "s1=$s1 s2=$s2")


        Text(
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(s5) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(16.dp),
            text = "I'm Constraint $startScreen $userId",
            style = TextStyle(
                color = Color.Red
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(s1) {

                }
                .padding(16.dp)
                .clickable {
                    ii++
                    navController?.navigate(Navs.Box.screenRoute + "/xxx?startScreen=" + startScreen)
                }
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
                .constrainAs(s2) {
                    start.linkTo(s1.end)
                }
                .padding(16.dp)
                .clickable {
                    ii++
                    navController?.navigate(Navs.Constraint.screenRoute + "/xxx?startScreen=" + startScreen)
                }
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
                .constrainAs(s3) {
                    top.linkTo(s1.bottom)
                }
                .padding(16.dp)
                .clickable {
                    ii++
                    navController?.navigate(Navs.Row.screenRoute + "/xxx?startScreen=" + startScreen)
                }
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
                .constrainAs(s4) {
                    start.linkTo(s3.end)
                    top.linkTo(s3.top)
                }
                .padding(16.dp)
                .clickable {
                    ii++
                    navController?.navigate(Navs.Col.screenRoute + "/xxx?startScreen=" + startScreen)
                }
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
fun ScreenConstraintPreview() {
    ComposePlay3Theme {
        ScreenConstraint("Android", "ios")
    }
}