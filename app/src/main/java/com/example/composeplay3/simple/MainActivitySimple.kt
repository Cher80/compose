package com.example.composeplay3.simple

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.composeplay3.fbannerwidth.MainContentsState2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class MainActivitySimple : ComponentActivity() {

    val myState = MutableStateFlow<SimpleData?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("gcompose", "onCreate")
        lifecycleScope.launch {
            repeat(100000) {
                delay(3000)
                Log.d("gcompose", "repeat $it")
                myState.value = SimpleData(
                    name = "Name $it",
                    count = it
                )
//                withTimeout(3000) {
//
//                }
            }
        }





        setContent {
            val simpleDataMutable: MutableState<SimpleData?> =
                mutableStateOf(null)
            val simpleDataMutable2: MutableState<SimpleData?> =
                mutableStateOf(null)

            lifecycleScope.launch {
                myState.collect { simpleData ->
                    Log.d("gcompose", "collect $simpleData")
                    simpleDataMutable.value = simpleData
                    simpleDataMutable2.value = SimpleData(
                        name = "dd",
                        count = 2
                    )
                }
            }

            SelfUpdateContent(
                simpleData = null,
                simpleData2 = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SelfUpdateContent(simpleData: SimpleData?, simpleData2: SimpleData?) {
    Log.d("gcompose", "SelfUpdateContent")


    Column {
        simpleData?.let {
            SimpleView(
                modifier = Modifier
                    .size(200.dp, 200.dp),
                simpleData = it
            )
        }
    }
    simpleData2?.let {
        SimpleView(
            modifier = Modifier
                .size(200.dp, 200.dp),
            simpleData = it
        )
    }
}