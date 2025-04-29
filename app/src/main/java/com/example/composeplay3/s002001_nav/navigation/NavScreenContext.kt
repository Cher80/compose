package com.example.composeplay3.s002001_nav.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.State

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



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NavScreenContext.getTabBarPaddingWithImeState(): State<Dp> = animateDpAsState(if (this.tabBarVisible && !WindowInsets.isImeVisible) this.tabBarHeight else 0.dp)

