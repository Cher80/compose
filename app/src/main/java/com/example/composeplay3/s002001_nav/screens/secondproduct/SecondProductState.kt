package com.example.composeplay3.s002001_nav.screens.secondproduct

data class SecondProductState(
    val productsCount: Int,
    val productsCountPlus: () -> Unit,
    val userId: String,
    val startScreen: String,
) {
    companion object {
        val TEST = SecondProductState(
            productsCount = 2,
            productsCountPlus = {},
            userId = "8923",
            startScreen = "Start"
        )
    }
}