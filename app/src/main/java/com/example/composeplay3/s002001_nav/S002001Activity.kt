package com.example.composeplay3.s002001_nav

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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeplay3.s002001_nav.navigation.AppNavigationController
import com.example.composeplay3.s002001_nav.navigation.NavEvent
import com.example.composeplay3.s002001_nav.navigation.Navs
import com.example.composeplay3.s002001_nav.screens.basemain.ScreenBaseMain
import com.example.composeplay3.s002001_nav.screens.basepayments.ScreenBasePayments
import com.example.composeplay3.s002001_nav.screens.basesettings.ScreenBaseSettings
import com.example.composeplay3.s002001_nav.screens.secondproduct.ScreenSecondProduct
import com.example.composeplay3.s002001_nav.screens.secondproduct.SecondProductState
import com.example.composeplay3.s002001_nav.screens.secondproduct.SecondProductViewModel
import com.example.composeplay3.s002001_nav.screens.secondproduct.SecondProductViewModelFactory
import com.example.composeplay3.s002001_nav.ui.nav.NavButtonsState
import com.example.composeplay3.ui.theme.ComposePlay3Theme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    var tabBarVisibility: Boolean = true
    val startTabRoute: String = Navs.ScreenBaseMain.screenRoute
    val tabs = listOf(Navs.ScreenBaseMain, Navs.ScreenBasePayments, Navs.ScreenBaseSettings)

    var mainContentsStateMutable = mutableStateOf<MainContentsState?>(null)
    val appNavigationController = AppNavigationController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleState()

        /**
         * внутри edge to edge WindowCompat.setDecorFitsSystemWindows(window, false)
         * так же убрать (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb() в Theme
         */
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
            val navController = rememberNavController()

            LaunchedEffect(Unit) {
                appNavigationController.navEvents.collect { navEvent ->
                    Log.d("gcompose", "navEvent = $navEvent")
                    when (navEvent) {
                        is NavEvent.Navigate -> {
                            navController.bottomNavigatonClick(
                                screenRoute = navEvent.route,
                                tabs = tabs
                            )
                        }

                        is NavEvent.TabBarVisibility -> {
                            tabBarVisibility = navEvent.visible
                            handleState()
                        }

                        is NavEvent.NO -> {}
                    }
                }
            }

            MainContent(
                navController = navController,
                mainContentsState = mainContentsStateMutable
            )
        }
    }

    @SuppressLint("RestrictedApi")
    fun handleState() {
        Log.d("asadff", "handleState")
        mainContentsStateMutable.value = MainContentsState(
            navButtonsState = NavButtonsState(
                gotoRoute = appNavigationController::gotoRoute,
                tabBarVisible = appNavigationController::tabBarVisible
            ),
            tabBarVisibility = tabBarVisibility,
            startTabRoute = startTabRoute,
            gotoRoute = appNavigationController::gotoRoute,
            tabStates = tabs.map { nav ->
                TabState(
                    nav = nav
                )
            }.toPersistentList()
        )
    }


    @SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainContent(
        navController: NavHostController,
        mainContentsState: MutableState<MainContentsState?>
    ) {
        Log.d(
            "gcompose",
            "MainContent tabBarVisibility=${mainContentsState.value?.tabBarVisibility}"
        )
        val mainContentsStateValue = mainContentsState.value ?: return
        Log.d("gcompose", "MainContent DO")

        ComposePlay3Theme {
            // A surface container using the 'background' color from the theme
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {

                Box(modifier = Modifier.size(width = 100.dp, height = 100.dp)) {
                    Log.d("gcompose", "Box")
                    Text(text = "xxx")
                }

                NavHost(
                    modifier = Modifier.padding(top = 0.dp, bottom = 0.dp),
                    navController = navController,
                    startDestination = Navs.ScreenBaseMain.screenRoute
                ) {
                    Log.d("gcompose", "NavHost")
                    composable(route = Navs.ScreenBaseMain.screenRoute) {
                        Log.d("gcompose", "NavHost ScreenBaseMain")
                        ScreenBaseMain(
                            navButtonsState = mainContentsStateValue.navButtonsState
                        )
                    }
                    composable(
                        route = Navs.ScreenBasePayments.screenRoute,
                    ) {
                        Log.d("gcompose", "NavHost ScreenBasePayments")
                        ScreenBasePayments(
                            navButtonsState = mainContentsStateValue.navButtonsState
                        )
                    }
                    composable(route = Navs.ScreenBaseSettings.screenRoute) {
                        Log.d("gcompose", "NavHost ScreenBaseSettings")
                        ScreenBaseSettings(
                            navButtonsState = mainContentsStateValue.navButtonsState
                        )
                    }

                    composable(route = "${Navs.ScreenSecondProduct.screenRoute}/{userId}?startScreen={startScreen}") { backStackEntry ->
                        Log.d("gcompose", "NavHost ScreenSecondProduct")

                        val viewModel: SecondProductViewModel = viewModel(
                            modelClass = SecondProductViewModel::class.java,
                            factory = SecondProductViewModelFactory(),
                            extras = MutableCreationExtras(initialExtras = defaultViewModelCreationExtras).apply {
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
                    Log.d("gcompose", "NavigationBar")

                    // recompose на изменение стека !! не убирать
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    Log.d("gcompose", "NavigationBar currentDestination = $currentDestination")

                    val backEnabled = mainContentsStateValue.tabStates.any {
                        it.nav.screenRoute == navController.currentDestination?.route &&
                                navController.currentDestination?.route != Navs.ScreenBaseMain.screenRoute
                    }
                    BackHandler(enabled = backEnabled) {
//                BackHandler(enabled = mainContentsStateValue.tabBarBackEnabled) {
                        Log.d("gcompose", "BackHandler")
                        mainContentsStateValue.gotoRoute(Navs.ScreenBaseMain.screenRoute)
//                    navController.bottomNavigatonClick(
//                        Navs.ScreenBaseMain.screenRoute,
//                        mainContentsStateValue.tabs
//                    )
                    }

                    mainContentsStateValue.tabStates.forEachIndexed { index, tabState ->

                        val selected = navController.currentBackStack.value.any {
                            it.destination.route == tabState.nav.screenRoute
                        }

//                    val selected = navController.currentBackStack.value.any {
//                        mainContentsStateValue.selectedTabRoute == nav.screenRoute
//                    }

                        NavigationBarItem(
                            icon = { Icon(painterResource(id = tabState.nav.icon), null) },
                            label = { Text(text = tabState.nav.title) },
                            selected = selected, // tabState.selected,
                            onClick = {
                                //mainContentsState.value.navState.goto(nav.screenRoute)
                                mainContentsStateValue.gotoRoute(tabState.nav.screenRoute)
                                //navController.bottomNavigatonClick(nav.screenRoute, mainContentsStateValue.tabs)
                            }
                        )
                    }
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
    val tabStates: ImmutableList<TabState>,
    val gotoRoute: (String) -> Unit
)
