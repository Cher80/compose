package com.example.composeplay3.s002001_nav.ui.nav

data class NavButtonsState(
    val gotoRoute: (String) -> Unit,
    val changeTabBarVisibility: (Boolean) -> Unit
) {
    companion object {
        val TEST = NavButtonsState(
            gotoRoute = {},
            changeTabBarVisibility = {}
        )
    }
}
