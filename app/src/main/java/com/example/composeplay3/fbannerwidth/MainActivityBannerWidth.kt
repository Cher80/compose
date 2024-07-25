package com.example.composeplay3.fbannerwidth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.lifecycleScope
import com.example.composeplay3.ui.theme.ComposePlay3Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainActivityBannerWidth : ComponentActivity() {


    var banners = 0
    var isBig = false

    fun handleState() {
        stateFlow.value =
            MainContentsState2(
                state1 = BannerState(
                    bannerId = "banner $banners",
                    onClick = ::onClickBanner
                ),
                state2 = BannerState(
                    bannerId = "banner $banners",
                    onClick = ::onClickBanner
                ),
                isBig = isBig,
                buttClick = ::onClickButt

            )
    }


    var stateFlow: MutableStateFlow<MainContentsState2?> = MutableStateFlow(null)


    fun onClickBanner() {
        banners++
        handleState()
    }

    fun onClickButt() {
        isBig = !isBig
        handleState()
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            handleState()
        }

        val mainContentsStateRemeber2: MutableState<MainContentsState2> =
            mutableStateOf(MainContentsState2())

        setContent {


            LaunchedEffect(key1 = "d") {
                lifecycleScope.launch {
                    stateFlow.collect { mainContentState ->
                        mainContentState?.let {
                            mainContentsStateRemeber2.value = it
                        }
                    }
                }
            }




            TestContent(
                mainContentsStateMutable2 = mainContentsStateRemeber2,
            )

        }
    }
}

@Composable
fun TestContent(
    mainContentsStateMutable2: MutableState<MainContentsState2>,
) {

    //Log.d("gcompose", "TestContent mainContentsStateMutable2 = $mainContentsStateMutable2")
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff00ffFF), RoundedCornerShape(16.dp))
    ) {
        val composables = createRefs()
        val s1 = composables.component1()
        val s2 = composables.component2()
        val s3 = composables.component3()


        mainContentsStateMutable2.value.state1?.let {
            val w = if (mainContentsStateMutable2.value.isBig) {
                350.dp
            } else {
                100.dp
            }
            BannerView(
                state = it,
                modifier = Modifier
                    .animateContentSize()
                    .wrapContentHeight()
                    .width(w)
                    .constrainAs(s1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(s2.start)
                    }

            )
        }


        mainContentsStateMutable2.value.state2?.let {
            BannerView2(
                state = it,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .constrainAs(s2) {
                        top.linkTo(parent.top)
                        start.linkTo(s1.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )
        }

        mainContentsStateMutable2.value.buttClick?.let {
            Button(
                onClick = it,
                modifier = Modifier
                    .wrapContentWidth()
                    .constrainAs(s3) {
                        top.linkTo(s1.bottom)
                        start.linkTo(parent.start)
                    }
            ) {
                Text("Button")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TestContentPreview() {
    val state = remember {
        mutableStateOf(
            MainContentsState2(
                state1 = BannerState(
                    bannerId = "banner x",
                    onClick = {}
                ),
                state2 = BannerState(
                    bannerId = "banner x",
                    onClick = {}
                ),
                buttClick = {}
            )
        )
    }

    ComposePlay3Theme {
        TestContent(
            mainContentsStateMutable2 = state
        )
    }
}

data class MainContentsState2(
    val state1: BannerState? = null,
    val state2: BannerState? = null,
    val isBig: Boolean = false,
    val buttClick: (() -> Unit)? = null
)
