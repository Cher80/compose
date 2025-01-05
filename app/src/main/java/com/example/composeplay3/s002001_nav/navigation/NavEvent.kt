package com.example.composeplay3.s002001_nav.navigation

sealed class NavEvent {
    data class Navigate(
        val route: String
    ): NavEvent()

    data class TabBarVisibility(
        val visible: Boolean
    ): NavEvent()

    data object NO: NavEvent()
}