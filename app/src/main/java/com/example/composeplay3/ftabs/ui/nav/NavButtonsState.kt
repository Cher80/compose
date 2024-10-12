package com.example.composeplay3.ftabs.ui.nav

data class NavButtonsState(
    val goto: (String) -> Unit,
    val tabBarVisible: (Boolean) -> Unit
) {
    companion object {
        val NavButtonsStateTest = NavButtonsState(
            goto = {},
            tabBarVisible = {}
        )
    }
}
