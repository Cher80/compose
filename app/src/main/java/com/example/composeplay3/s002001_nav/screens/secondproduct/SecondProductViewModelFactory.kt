package com.example.composeplay3.s002001_nav.screens.secondproduct

import android.util.Log
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras

class SecondProductViewModelFactory : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        Log.d("gcompose", "SecondProductViewModelFactory create modelClass=$modelClass")
//        return SecondProductViewModel() as T
//    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//        "userId" to backStackEntry.arguments?.getString("userId"),
//        "startScreen" to backStackEntry.arguments?.getString("startScreen")
        return SecondProductViewModel(
            savedStateHandle = extras.createSavedStateHandle(),
            arguments = extras[DEFAULT_ARGS_KEY]
        ) as T
    }
}

