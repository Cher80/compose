package com.example.composeplay3.s002001_nav.ui.nav

data class NavButtonsState(
    val tabBarActuallyVisible: Boolean,
    val gotoRoute: (String) -> Unit,
    val changeTabBarVisibility: (Boolean) -> Unit
) {
    companion object {
        val TEST = NavButtonsState(
            tabBarActuallyVisible = true,
            gotoRoute = {},
            changeTabBarVisibility = {}
        )
    }
}
