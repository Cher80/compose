package com.example.composeplay3.s002001_nav.screens.secondproduct

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeplay3.s002001_nav.navigation.NavScreenContext
import com.example.composeplay3.s002001_nav.ui.nav.NavButtons
import com.example.composeplay3.s002001_nav.ui.nav.NavButtonsState
import com.example.composeplay3.ui.theme.ComposePlay3Theme
import kotlinx.collections.immutable.minus
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.plus


@Composable
fun ScreenSecondProduct(
    modifier: Modifier,
    secondProductState: SecondProductState?,
    flyHeartAction: Int?,
    navScreenContext: NavScreenContext,
    navButtonsState: NavButtonsState
) {
    if (secondProductState == null) return

    val bottomPadding: Dp by animateDpAsState(if (navScreenContext.tabBarVisible) navScreenContext.tabBarHeight else 0.dp)

    var products by remember {
        mutableIntStateOf(1)
    }

    var flyHearts by remember {
        mutableStateOf(persistentSetOf<Int>())
    }

    var finishedHeart: (Int)-> Unit = remember{
        { id ->
            val pos = flyHearts.indexOf(id)
            Log.d(
                "gcompose",
                "ScreenSecondProduct finishedHeart id=$id pos=$pos flyHearts.value=${flyHearts}"
            )
            flyHearts = flyHearts.minus(id)
            Log.d(
                "gcompose",
                "ScreenSecondProduct finishedHeart id=$id pos=$pos minused flyHearts.value=${flyHearts}"
            )
        }
    }


    LaunchedEffect(key1 = flyHeartAction) {
        Log.d("gcompose", "ScreenSecondProduct LaunchedEffect flyHeartAction=$flyHeartAction")
        if (flyHeartAction != null) {
            flyHearts = flyHearts.plus(flyHeartAction)
        }
    }

    Log.d(
        "gcompose",
        "ScreenSecondProduct flyHearts.value=${flyHearts} flyHeartAction=$flyHeartAction products=${products} productsCount=${secondProductState?.productsCount} userId=${secondProductState.userId} startScreen=${secondProductState?.startScreen}"
    )



    Box(modifier = modifier
        .fillMaxSize()
        .background(Color(0xFF64965C), RoundedCornerShape(46.dp))
    ) {

        Box(modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(Color(0xFF64965C), RoundedCornerShape(46.dp))
            .padding(bottom = bottomPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Log.d("gcompose", "ScreenSecondProduct Column compose")
                Text(
                    modifier = Modifier
                        .wrapContentWidth(),
                    text = "ScreenSecondProduct productsRemember=${products} productsCountViewmodel=${secondProductState.productsCount} userId=${secondProductState.userId} startScreen=${secondProductState.startScreen}",
                    style = TextStyle(
                        color = Color.Black
                    )
                )

                Button(
                    onClick = {
                        products++
                    }) {
                    Text("Plus products")
                }

                Button(
                    onClick = secondProductState.productsCountPlus
                ) {
                    Text("Plus products viewmodel")
                }

                Button(
                    onClick = secondProductState.flyHeart
                ) {
                    Text("Fly heart")
                }

                NavButtons(
                    navButtonsState = navButtonsState
                )
            }

            Box(
                modifier = Modifier
                    .width(330.dp)
                    .height(230.dp)
                    .background(Color.White)
                    .align(Alignment.BottomEnd)
            ) {
                Log.d("gcompose", "ScreenSecondProduct BOX compose")

                for (i in 0 until flyHearts.size) {
                    val id = flyHearts.elementAt(i)
                    key(id) {
                        ScreenSecondHeart(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .width(64.dp)
                                .height(64.dp),
                            id = id,
                            finished = finishedHeart
                        )
                    }
                }
            }

//            Box(
//                modifier = Modifier.width(130.dp).height(400.dp).background(color = Color.Red)
//                    .align(Alignment.BottomEnd)
//            ) {
//                Button(
//                    modifier = Modifier.align(Alignment.BottomStart),
//                    onClick = {}) {
//                    Text("Bottom")
//                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenSecondProductPreview() {
    ComposePlay3Theme {
        ScreenSecondProduct(
            modifier = Modifier,
            secondProductState = SecondProductState.TEST,
            flyHeartAction = null,
            navScreenContext = NavScreenContext.TEST,
            navButtonsState = NavButtonsState.TEST,
        )
    }
}