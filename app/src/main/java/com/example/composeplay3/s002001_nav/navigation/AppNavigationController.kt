package com.example.composeplay3.s002001_nav.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class AppNavigationController(

) {
    val navEvents: MutableSharedFlow<NavEvent> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )


    fun gotoRoute(route: String) {
        navEvents.tryEmit(
            NavEvent.Navigate(
                route = route
            )
        )
    }

    fun tabBarVisible(visible: Boolean) {
        navEvents.tryEmit(
            NavEvent.TabBarVisibility(
                visible = visible
            )
        )
    }
}
