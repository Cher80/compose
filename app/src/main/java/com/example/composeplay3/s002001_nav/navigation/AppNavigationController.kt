package com.example.composeplay3.s002001_nav.navigation

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.composeplay3.s002001_nav.SheetOne
import com.example.composeplay3.s002001_nav.screens.basemain.ScreenBaseMain
import com.example.composeplay3.s002001_nav.screens.basepayments.ScreenBasePayments
import com.example.composeplay3.s002001_nav.screens.basesettings.ScreenBaseSettings
import com.example.composeplay3.s002001_nav.screens.secondproduct.ScreenSecondProduct
import com.example.composeplay3.s002001_nav.screens.secondproduct.SecondProductViewModel
import com.example.composeplay3.s002001_nav.screens.secondproduct.SecondProductViewModelFactory
import com.example.composeplay3.s002001_nav.ui.nav.NavButtonsState
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class AppNavigationController(

) {

    lateinit var activity: ComponentActivity

    val navButtonsState = NavButtonsState(
        gotoRoute = ::gotoRoute,
        tabBarVisible = ::tabBarVisible
    )

    val destinations: PersistentSet<Destination> = persistentSetOf(
        Destination(
            route = "ScreenBaseMain",
            title = "ScreenBaseMainTitle",
            isTab = true,
            isStartDestination = true,
            icon = androidx.core.R.drawable.ic_call_decline
        ) { backStackEntry ->
            Log.d("gcompose", "NavHost ScreenBaseMain")
            ScreenBaseMain(
                navButtonsState = navButtonsState
            )
        },

        Destination(
            route = "ScreenBasePayments",
            title = "ScreenBasePaymentsTitle",
            isTab = true,
            isStartDestination = false,
            icon = androidx.core.R.drawable.ic_call_decline
        ) { backStackEntry ->
            Log.d("gcompose", "NavHost ScreenBasePayments")
            ScreenBasePayments(
                navButtonsState = navButtonsState
            )
        },

        Destination(
            route = "ScreenBaseSettings",
            title = "ScreenBaseSettingsTitle",
            isTab = true,
            isStartDestination = false,
            icon = androidx.core.R.drawable.ic_call_answer_video_low
        ) { backStackEntry ->
            Log.d("gcompose", "NavHost ScreenBaseSettings")
            ScreenBaseSettings(
                navButtonsState = navButtonsState
            )
        },

        Destination(
            route = "ScreenSecondProduct/{userId}?startScreen={startScreen}",
            title = "ScreenSecondProductTitle",
            isTab = false,
            isStartDestination = false,
            icon = androidx.core.R.drawable.ic_call_answer_video_low
        ) { backStackEntry ->
            Log.d("gcompose", "NavHost ScreenSecondProduct")

            val viewModel: SecondProductViewModel = viewModel(
                modelClass = SecondProductViewModel::class.java,
                factory = SecondProductViewModelFactory(),
                extras = MutableCreationExtras(initialExtras = activity.defaultViewModelCreationExtras).apply {
                    backStackEntry.arguments?.let {
                        set(
                            DEFAULT_ARGS_KEY, it
                        )
                    }
                }
            )

            ScreenSecondProduct(
                secondProductState = viewModel.secondProductStateFlow.collectAsState().value,
                flyHeartAction = viewModel.flyHeartAction.collectAsState(null).value,
                navButtonsState = navButtonsState
            )
        },

        Destination(
            route = "SheetOne/{userId}?startScreen={startScreen}",
            title = "SheetOneTitle",
            isTab = false,
            isStartDestination = false,
            icon = androidx.core.R.drawable.ic_call_answer_video_low
        ) { backStackEntry ->
            Log.d("gcompose", "NavHost SheetOne")
            SheetOne(
                userId = backStackEntry.arguments?.getString("userId") ?: "",
                startScreen = backStackEntry.arguments?.getString("startScreen") ?: "",
            )
        },


        )


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


    data class Destination(
        val route: String,
        val title: String,
        val isTab: Boolean,
        val isStartDestination: Boolean,
        @DrawableRes val icon: Int? = null,
        val content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
    )


//    val arguments: List<NamedNavArgument> = emptyList(),
//    val deepLinks: List<NavDeepLink> = emptyList(),
//    val enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
//    val exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
//    val popEnterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
//    val popExitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
//    val content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
}
