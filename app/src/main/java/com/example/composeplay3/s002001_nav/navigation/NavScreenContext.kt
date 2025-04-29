package com.example.composeplay3.s002001_nav.navigation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.Boolean

data class NavScreenContext(
    val tabBarVisible: Boolean,
    val tabBarHeight: Dp = TAB_BAR_HEIGHT
) {
    companion object {
        val TAB_BAR_HEIGHT = 80.dp

        val TEST = NavScreenContext(
            tabBarVisible = true
        )
    }
}
