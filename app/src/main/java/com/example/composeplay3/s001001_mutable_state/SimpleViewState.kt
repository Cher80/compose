package com.example.composeplay3.s001001_mutable_state

data class SimpleViewState(
    val name: String,
    val count: Int,
    val pokeButtonText: String,
    val pokeButtonClick: () -> Unit,
    val addUserClick: () -> Unit,
    val poked: Int
) {
    companion object {
        val TEST = SimpleViewState(
            name = "test",
            count = 0,
            pokeButtonText = "test",
            pokeButtonClick = {},
            addUserClick = {},
            poked = 0
        )
    }
}
