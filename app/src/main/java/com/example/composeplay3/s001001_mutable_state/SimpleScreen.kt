package com.example.composeplay3.s001001_mutable_state

data class SimpleScreen(
    val simpleViewState1: SimpleViewState,
    val simpleViewState2: SimpleViewState,
    val usersViewState: UsersViewState
) {
    companion object {
        val TEST = SimpleScreen(
            simpleViewState1 = SimpleViewState.TEST,
            simpleViewState2 = SimpleViewState.TEST,
            usersViewState = UsersViewState.TEST
        )
    }
}
