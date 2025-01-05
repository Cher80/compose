package com.example.composeplay3.s002001_nav.screens.secondproduct

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SecondProductViewModel: ViewModel() {

    var productsCount = 0
    val secondProductStateFlow = MutableStateFlow<SecondProductState?>(null)
    var userId: String = ""
    var startScreen: String = ""

    init {
        Log.d("gcompose", "SecondProductViewModel init")
    }

    fun onCreate(userId: String, startScreen: String) {
        Log.d("gcompose", "SecondProductViewModel onCreate")
        this.userId = userId
        this.startScreen = startScreen
        handleState()
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
            productsCountPlus = ::productsCountPlus
        )
    }
}

