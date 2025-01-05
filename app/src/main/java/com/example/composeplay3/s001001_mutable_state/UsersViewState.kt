package com.example.composeplay3.s001001_mutable_state

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class UsersViewState(
    val title: Title,
    val users: ImmutableList<String>
) {
    companion object {
        val TEST = UsersViewState(
            title = Title.TEST,
            users = persistentListOf("user1", "user2")
        )
    }

    data class Title(
        val title: String
    ) {
        companion object {
            val TEST = Title(
                title = "title"
            )
        }
    }
}
