package com.example.composeplay3.ftabs.navigation

sealed class NavEvent {
    data class Navigate(
        val route: String
    ): NavEvent()

    data object NO: NavEvent()
}