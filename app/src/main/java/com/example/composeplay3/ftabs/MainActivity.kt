package com.example.composeplay3.ftabs

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeplay3.ftabs.navigation.NavEvent
import com.example.composeplay3.ftabs.navigation.Navs
import com.example.composeplay3.ftabs.screens.basemain.ScreenBaseMain
import com.example.composeplay3.ftabs.screens.basepayments.ScreenBasePayments
import com.example.composeplay3.ftabs.screens.basesettings.ScreenBaseSettings
import com.example.composeplay3.ftabs.screens.secondproduct.ScreenSecondProduct
import com.example.composeplay3.ftabs.ui.nav.NavButtonsState
import com.example.composeplay3.ui.theme.ComposePlay3Theme
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    var tabBarVisibility: Boolean = true
    val startTabRoute: String = Navs.ScreenBaseMain.screenRoute
    var selectedTabRoute: String = Navs.ScreenBaseMain.screenRoute
    val tabs = listOf(Navs.ScreenBaseMain, Navs.ScreenBasePayments, Navs.ScreenBaseSettings)
    var navController: NavHostController? = null

    var mainContentsStateMutable = mutableStateOf<MainContentsState?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navEvents: MutableSharedFlow<NavEvent> = MutableSharedFlow(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

        handleState()

        lifecycleScope.launch {
            delay(5000)
//            navEvents.emit(
//                NavEvent.Navigate(
//                    route = Navs.ScreenBaseSettings.screenRoute
//                )
//            )
        }
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        // Так же убрать
        // (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
        // в Theme


        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        if (Build.VERSION.SDK_INT >= 29) {
            window.isNavigationBarContrastEnforced = false
        }


        setContent {
            navController = rememberNavController()

            val navEventState = remember {
                mutableStateOf<NavEvent>(NavEvent.NO)
            }


//            val mainContentsStateRemeber = remember {
//                mainContentsStateMutable
//            }


            LaunchedEffect(Unit) {
                navEvents.collect { navEvent ->
                    Log.d("asadff", "navEvent = $navEvent")
                    navEventState.value = navEvent
                }
            }

            MainContent(
                navController = navController!!,
                mainContentsState = mainContentsStateMutable,
                navEventsState = navEventState
            )
        }
    }

    @SuppressLint("RestrictedApi")
    fun handleState() {
        Log.d("asadff", "handleState")
        mainContentsStateMutable.value = MainContentsState(
            navButtonsState = NavButtonsState(
                goto = { route ->
                    navController.bottomNavigatonClick(route, tabs)
                    handleState()
//                    navEventState.value = NavEvent.Navigate(
//                        route = route
//                    )
                },
                tabBarVisible = {}
            ),
            tabBarVisibility = tabBarVisibility,
            startTabRoute = startTabRoute,
            tabBarClicked = { route ->
                navController.bottomNavigatonClick(route, tabs)
                handleState()
            },
            tabBarBackEnabled = tabs.any {
                    it.screenRoute == navController?.currentDestination?.route &&
                            navController?.currentDestination?.route != Navs.ScreenBaseMain.screenRoute
                },
            tabBarBackClicked = {
                navController.bottomNavigatonClick(Navs.ScreenBaseMain.screenRoute, tabs)
                handleState()
            },
            tabStates = tabs.map { nav ->
                TabState(
                    nav = nav,
                    selected = navController?.currentBackStack?.value?.any {
                        it.destination.route == nav.screenRoute
                    } == true
                )
            }
        )
    }
}


@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    navController: NavHostController,
    mainContentsState: MutableState<MainContentsState?>,
    navEventsState: MutableState<NavEvent>
) {
    Log.d("asadff", "MainContent tabBarBackEnabled=${mainContentsState.value?.tabBarBackEnabled} tabBarVisibility=${mainContentsState.value?.tabBarVisibility}")
    val mainContentsStateValue = mainContentsState.value ?: return
    Log.d("asadff", "MainContent DO")
//    when (val navEvent = navEventsState.value) {
//        is NavEvent.Navigate -> {
//             navController.bottomNavigatonClick(navEvent.route)
//        }
//
//        is NavEvent.NO -> {}
//    }

    ComposePlay3Theme {
        // A surface container using the 'background' color from the theme
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
        ) {

            Box(modifier = Modifier.size(width = 100.dp, height = 100.dp)) {
                Log.d("asadff", "Box")
                Text(text = "xxx")
            }

            NavHost(
                modifier = Modifier.padding(top = 0.dp, bottom = 0.dp),
                navController = navController, startDestination = Navs.ScreenBaseMain.screenRoute
            ) {
                Log.d("asadff", "NavHost")
                composable(route = Navs.ScreenBaseMain.screenRoute) {
                    Log.d("asadff", "NavHost ScreenBaseMain")
                    ScreenBaseMain(
                        navButtonsState = mainContentsStateValue.navButtonsState
                    )
                }
                composable(
                    route = Navs.ScreenBasePayments.screenRoute,
                ) {
                    Log.d("asadff", "NavHost ScreenBasePayments")
                    ScreenBasePayments(
                        navButtonsState = mainContentsStateValue.navButtonsState
                    )
                }
                composable(route = Navs.ScreenBaseSettings.screenRoute) {
                    Log.d("asadff", "NavHost ScreenBaseSettings")
                    ScreenBaseSettings(
                        navButtonsState = mainContentsStateValue.navButtonsState
                    )
                }

                composable(route = "${Navs.ScreenSecondProduct.screenRoute}/{userId}?startScreen={startScreen}") { backStackEntry ->
                    Log.d("asadff", "NavHost ScreenSecondProduct")
                    ScreenSecondProduct(
                        //viewModel = viewModel(modelClass = ScreenBoxViewModel::class.java),
                        userId = backStackEntry.arguments?.getString("userId") ?: "",
                        startScreen = backStackEntry.arguments?.getString("startScreen") ?: "",
                        navButtonsState = mainContentsStateValue.navButtonsState
                    )
                }


                composable(route = "${Navs.SheetOne.screenRoute}/{userId}?startScreen={startScreen}") { backStackEntry ->
                    SheetOne(
                        userId = backStackEntry.arguments?.getString("userId") ?: "",
                        startScreen = backStackEntry.arguments?.getString("startScreen") ?: "",
                        navController = navController
                    )
                }
            }


            NavigationBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .systemBarsPadding(),
                contentColor = Color.Black
            ) {
                Log.d("asadff", "NavigationBar")

//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentDestination = navBackStackEntry?.destination
//                Log.d("asadff", "currentDestination = $currentDestination")

//                val backEnabled = mainContentsStateValue.tabStates.any {
//                    it.nav.screenRoute == navController.currentDestination?.route &&
//                            navController.currentDestination?.route != Navs.ScreenBaseMain.screenRoute
//                }
                BackHandler(enabled = mainContentsStateValue.tabBarBackEnabled) {
                    Log.d("asadff", "BackHandler")
                    mainContentsStateValue.tabBarBackClicked()
//                    navController.bottomNavigatonClick(
//                        Navs.ScreenBaseMain.screenRoute,
//                        mainContentsStateValue.tabs
//                    )
                }

                mainContentsStateValue.tabStates.forEachIndexed { index, tabState ->

//                    val selected = navController.currentBackStack.value.any {
//                        it.destination.route == nav.screenRoute
//                    }

//                    val selected = navController.currentBackStack.value.any {
//                        mainContentsStateValue.selectedTabRoute == nav.screenRoute
//                    }

                    NavigationBarItem(
                        icon = { Icon(painterResource(id = tabState.nav.icon), null) },
                        label = { Text(text = tabState.nav.title) },
                        selected = tabState.selected,
                        onClick = {
                            //mainContentsState.value.navState.goto(nav.screenRoute)
                            mainContentsStateValue.tabBarClicked(tabState.nav.screenRoute)
                            //navController.bottomNavigatonClick(nav.screenRoute, mainContentsStateValue.tabs)
                        }
                    )
                }
            }
        }
    }
}


@SuppressLint("RestrictedApi")
fun NavController?.bottomNavigatonClick(screenRoute: String, tabs: List<Navs>) {
    val navController = this
    navController?.navigate(screenRoute) {

        val currentBaseTab = navController?.currentBackStack?.value?.get(1)?.destination
        val curTabNavDestination = navController?.graph?.findNode(
            route = screenRoute
        )

        val isTab = tabs.any { it.screenRoute == screenRoute }
        if (isTab && currentBaseTab != null) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(currentBaseTab.id) {
                saveState = curTabNavDestination != currentBaseTab
                inclusive = true
            }

            if (curTabNavDestination != currentBaseTab) {
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    }
}

data class MainContentsState(
    val navButtonsState: NavButtonsState,
    val tabBarVisibility: Boolean,
    val startTabRoute: String,
    val tabBarClicked: (String) -> Unit,
    val tabBarBackEnabled: Boolean,
    val tabBarBackClicked: () -> Unit,
    val tabStates: List<TabState>
)

