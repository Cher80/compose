package com.example.composeplay3.s002001_nav.screens.secondproduct

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class SecondProductViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val arguments: Bundle?
): ViewModel() {

    val secondProductStateFlow = MutableStateFlow<SecondProductState?>(null)
    val flyHeartAction = MutableSharedFlow<Int?>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private var productsCount = 0
    private var userId: String
    private var startScreen: String = ""

    init {
        userId= arguments?.getString("userId") ?: ""
        startScreen = arguments?.getString("startScreen") ?: ""
        actionsProduct()
        Log.d("gcompose", "SecondProductViewModel init userId=$userId startScreen=$startScreen")
        handleState()
    }

    fun actionsProduct() {
//        viewModelScope.launch {
//            repeat(20) {
//                delay(3000)
//                secondProductStateAction.tryEmit(it)
//            }
//        }
    }

    var f = 0
    fun flyHeart() {
        f += 1000
        flyHeartAction.tryEmit(f)
    }

    fun productsCountPlus() {
        productsCount++
        handleState()
    }

    fun handleState() {
        secondProductStateFlow.value = SecondProductState(
            userId = userId,
            startScreen = startScreen,
            productsCount = productsCount,
            productsCountPlus = ::productsCountPlus,
            flyHeart = ::flyHeart
        )
    }

    override fun onCleared() {
        Log.d("gcompose", "SecondProductViewModel onCleared")
        super.onCleared()
    }
}

