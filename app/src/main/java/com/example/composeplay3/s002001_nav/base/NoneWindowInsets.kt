package com.example.composeplay3.s002001_nav.base

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


val NONE_WINDOW_INSETS = object : WindowInsets {
    override fun getBottom(density: Density): Int {
        return 0
    }

    override fun getLeft(
        density: Density,
        layoutDirection: LayoutDirection
    ): Int {
        return 0
    }

    override fun getRight(
        density: Density,
        layoutDirection: LayoutDirection
    ): Int {
        return 0
    }

    override fun getTop(density: Density): Int {
        return 0
    }

}