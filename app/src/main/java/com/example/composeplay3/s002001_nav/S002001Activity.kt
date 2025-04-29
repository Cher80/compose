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
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeplay3.s002001_nav.navigation.AppNavigationController
import com.example.composeplay3.s002001_nav.navigation.NavEvent
import com.example.composeplay3.s002001_nav.navigation.NavScreenContext
import com.example.composeplay3.ui.theme.ComposePlay3Theme
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.PersistentSet

class MainActivity : ComponentActivity() {

    var mainContentsStateMutable = mutableStateOf<MainContentsState?>(null)
    val appNavigationController = AppNavigationController().apply {
        activity = this@MainActivity
    }

    @OptIn(ExperimentalLayoutApi::class)
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
                                destinations = appNavigationController.destinations
                            )
                        }

                        is NavEvent.TabBarVisibility -> {
//                            handleState()
                        }

                        is NavEvent.NO -> {}
                    }
                }
            }

            //appNavigationController.changeImeVisibility(WindowInsets.isImeVisible)
            MainContent(
                navController = navController,
                mainContentsState = mainContentsStateMutable,
                navScreenContext = appNavigationController.navScreenContext.value
            )
        }
    }

    @SuppressLint("RestrictedApi")
    fun handleState() {
        Log.d("asadff", "handleState")
        mainContentsStateMutable.value = MainContentsState(
            destinations = appNavigationController.destinations,
            gotoRoute = appNavigationController::gotoRoute
        )
    }


    @SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    @Composable
    fun MainContent(
        navController: NavHostController,
        navScreenContext: NavScreenContext,
        mainContentsState: MutableState<MainContentsState?>
    ) {
        Log.d(
            "gcompose",
            "MainContent tabBarVisibility=${navScreenContext.tabBarVisible}"
        )
        val mainContentsStateValue = mainContentsState.value ?: return
        Log.d("gcompose", "MainContent DO")


        //navController.graph.addDestination(navDestination)

        ComposePlay3Theme {
            // A surface container using the 'background' color from the theme

//            val navHost = createRef()
//            val navBar = createRef()
//            val navBarHolder = createRef()


//            val bottomPadding: Dp by animateDpAsState(if (navScreenContext.tabBarVisible && !WindowInsets.isImeVisible) 0.dp else 70.dp)
//            val bottomAlpha: Float by animateFloatAsState(if (navScreenContext.tabBarVisible && !WindowInsets.isImeVisible) 1f else 0f)
            val bottomPadding: Dp by animateDpAsState(if (navScreenContext.tabBarVisible) 0.dp else navScreenContext.tabBarHeight)
            val bottomAlpha: Float by animateFloatAsState(if (navScreenContext.tabBarVisible) 1f else 0f)

            val constraintSetVisibleTabBar = ConstraintSet {
                val navHost = createRefFor("navHost")
                val navBarHolder = createRefFor("navBarHolder")
                val bottomBox = createRefFor("bottomBox")
                val topBox = createRefFor("topBox")

                constrain(navHost) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(
                        anchor = parent.bottom,
                        margin = 0.dp // 80.dp
                    )
                }

                constrain(navBarHolder) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }

                constrain(bottomBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(navHost.bottom)
                }

                constrain(topBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(navHost.top)
                }
            }





            ConstraintLayout(
                animateChangesSpec = TweenSpec(),
//                animateChanges = true,
                constraintSet = constraintSetVisibleTabBar,
                modifier = Modifier
//                    .consumeWindowInsets(
//                        WindowInsets.systemBars.only(WindowInsetsSides.Vertical)
//                    )
                    .fillMaxSize()
//                    .height(500.dp)
//                    .width(300.dp)
                    .background(Color.Green)
//                    .systemBarsPadding()
            ) {

//                val bottomBox = createRef()
//                val topBox = createRef()

//                Box(modifier = Modifier.size(width = 100.dp, height = 100.dp)) {
//                    Log.d("gcompose", "Box")
//                    Text(text = "xxx")
//                }

                NavHost(
                    modifier = Modifier
                        .animateContentSize()
                        .layoutId("navHost")
//                        .constrainAs(navHost) {
//                            width = Dimension.fillToConstraints
//                            height = Dimension.fillToConstraints
//                            top.linkTo(parent.top)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//
//                            if (mainContentsStateValue.tabBarVisibility) {
//                                bottom.linkTo(navBarHolder.top)
//                            } else {
//                                bottom.linkTo(parent.bottom)
//                            }
//
//
//                        }
                        .fillMaxWidth()
//                        .fillMaxHeight()

//                        .fillMaxHeight()

//                        .fillMaxSize()
//                        .width(400.dp)
//                        .height(600.dp)
                    ,
                    navController = navController,
                    startDestination = mainContentsStateValue.destinations.first { it.isStartDestination }.route
                ) {


                    Log.d("gcompose", "NavHost")

                    mainContentsStateValue.destinations.forEach { destination ->
                        composable(
                            route = destination.route,
                            content = destination.content
                        )
                    }
                }


//                AnimatedVisibility(
//                    visible = mainContentsStateValue.tabBarVisibility,
//                    enter = slideInVertically(
//                        initialOffsetY = {
//                            it
//                        }
//                    ),//fadeIn() + expandVertically(),
//                    exit = slideOutVertically(
//                        targetOffsetY = {
//                            it
//                        }
//                    ),// shrinkVertically() + fadeOut(),
//                    modifier = Modifier
//                        .layoutId("navBarHolder")
////                        .constrainAs(navBarHolder) {
////                            bottom.linkTo(parent.bottom)
////                            start.linkTo(parent.start)
////                            end.linkTo(parent.end)
////                        }
//                ) {
                if (bottomAlpha != 0f) {
                    NavigationBar(
//                    windowInsets = NONE_WINDOW_INSETS,
                        modifier = Modifier
                            .systemBarsPadding()
                            .height(navScreenContext.tabBarHeight)
                            .layoutId("navBarHolder")
                            .graphicsLayer {
                                translationY = bottomPadding.toPx()
                                alpha = bottomAlpha
                            },

                        contentColor = Color.Black,
                    ) {
                        Log.d("gcompose", "NavigationBar")

                        // recompose на изменение стека !! не убирать
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        Log.d("gcompose", "NavigationBar currentDestination = $currentDestination")

                        val startRoute =
                            mainContentsStateValue.destinations.first { it.isStartDestination }.route
                        val backEnabled =
                            mainContentsStateValue.destinations.filter { it.isTab }.any {
                                it.route == navController.currentDestination?.route &&
                                        navController.currentDestination?.route != startRoute
                            }
                        BackHandler(enabled = backEnabled) {
                            Log.d("gcompose", "BackHandler")
                            mainContentsStateValue.gotoRoute(startRoute)
                        }

                        mainContentsStateValue.destinations.filter { it.isTab }
                            .forEachIndexed { index, tabDestination ->
                                val selected = navController.currentBackStack.value.any {
                                    it.destination.route == tabDestination.route
                                }

                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painterResource(id = tabDestination.icon!!),
                                            null
                                        )
                                    },
                                    label = { Text(text = tabDestination.title) },
                                    selected = selected,
                                    onClick = {
                                        mainContentsStateValue.gotoRoute(tabDestination.route)
                                    }
                                )
                            }
                    }
                }

//                }


                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .background(color = Color.Magenta)
                        .layoutId("bottomBox")
//                        .constrainAs(bottomBox) {
//                            bottom.linkTo(navHost.bottom)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        }
                ) {
                }

                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .background(color = Color.Magenta)
                        .layoutId("topBox")
//                        .constrainAs(topBox) {
//                            top.linkTo(navHost.top)
//                            start.linkTo(parent.start)
//                            end.linkTo(parent.end)
//                        }
                ) {
                }
            }
        }
    }
}


@SuppressLint("RestrictedApi")
fun NavController?.bottomNavigatonClick(
    screenRoute: String,
    destinations: PersistentSet<AppNavigationController.Destination>
) {
    val navController = this
    navController?.navigate(screenRoute) {

        val currentBaseTab = navController.currentBackStack.value[1].destination
        val curTabNavDestination = navController.graph.findNode(
            route = screenRoute
        )

        val isTab = destinations.filter { it.isTab }.any { it.route == screenRoute }
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
    val destinations: ImmutableSet<AppNavigationController.Destination>,
    val gotoRoute: (String) -> Unit
)
