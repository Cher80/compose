package com.example.composeplay3.s001001_mutable_state

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * репорты по классам
 * composeCompiler {
 *         reportsDestination = layout.buildDirectory.dir("compose_compiler")
 *         metricsDestination = layout.buildDirectory.dir("compose_compiler")
 *     }
 *
 * классы
 *     stable - то что compose может сам рассчитать, и не перерисовывать
 *     unstable - не может сам рассчитать и всегда делает рекомпозицию
 *
 * функции
 * restartable - может перерисовывать
 * skippable - может быть пропущена есть класс стейта stable
 *
 */

class S001001Activity : ComponentActivity() {

    private val simpleScreenFlow = MutableStateFlow<SimpleScreen?>(null)
    private var pokeFirst = 0
    private var pokeSecond = 0
    private var iter = 0
    private var users = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("gcompose", "onCreate")
        lifecycleScope.launch {
            repeat(100000) {
                delay(10000)
                iter++
                Log.d("gcompose", "Emit iter = $iter")
                handleState()
            }
        }


        setContent {
            Log.d("gcompose", "setContent")
            val simpleScreenMutable: MutableState<SimpleScreen> = remember {
                mutableStateOf(SimpleScreen.TEST)
            }


            LaunchedEffect(Unit) {
                simpleScreenFlow.collect { simpleScreen ->
                    if (simpleScreen != null) {
                        Log.d("gcompose", "Collect simpleScreen $simpleScreen")
                        simpleScreenMutable.value = simpleScreen
                    }
                }
            }

            SelfUpdateContent(
                simpleScreenMutable = simpleScreenMutable
            )
        }
    }

    private fun handleState() {
        Log.d("gcompose", "handleState")
        val simpleScreen = SimpleScreen(
            simpleViewState1 = SimpleViewState(
                name = "Name First $iter",
                count = iter,
                pokeButtonText = "Poke Second",
                pokeButtonClick = ::pokeSecondClick,
                addUserClick = ::addUserClick,
                poked = pokeFirst
            ),
            simpleViewState2 = SimpleViewState(
                name = "Name Second $iter",
                count = iter,
                pokeButtonText = "Poke First",
                pokeButtonClick = ::pokeFirstClick,
                addUserClick = ::addUserClick,
                poked = pokeSecond
            ),
            usersViewState = UsersViewState(
                title = UsersViewState.Title(title = "Zagolovok"),
                users = users.toPersistentList()
            )
        )
        simpleScreenFlow.value = simpleScreen
    }

    private fun pokeFirstClick() {
        pokeFirst++
        handleState()
    }

    private fun pokeSecondClick() {
        pokeSecond++
        handleState()
    }

    private fun addUserClick() {
        users.add("user:$iter,")
        handleState()
    }
}

@Composable
fun SelfUpdateContent(
    simpleScreenMutable: MutableState<SimpleScreen>,
) {
    Log.d("gcompose", "SelfUpdateContent")


    Column {
        SimpleView(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            simpleViewState = simpleScreenMutable.value.simpleViewState1
        )

        SimpleView(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            simpleViewState = simpleScreenMutable.value.simpleViewState2
        )

        UsersView(
            modifier = Modifier.padding(20.dp),
            usersViewState = simpleScreenMutable.value.usersViewState
        )
    }
}