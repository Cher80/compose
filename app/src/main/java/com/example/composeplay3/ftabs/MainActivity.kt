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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeplay3.ftabs.navigation.AppNavigationController
import com.example.composeplay3.ftabs.navigation.Navs
import com.example.composeplay3.ftabs.screens.basemain.ScreenBaseMain
import com.example.composeplay3.ftabs.screens.basepayments.ScreenBasePayments
import com.example.composeplay3.ftabs.screens.basesettings.ScreenBaseSettings
import com.example.composeplay3.ftabs.screens.secondproduct.ScreenSecondProduct
import com.example.composeplay3.ui.theme.ComposePlay3Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

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
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Green)
//            ) {
//                Text(text = "dsdds", modifier = Modifier.align(Alignment.TopCenter).systemBarsPadding())
//                Text(text = "dsdds", modifier = Modifier.align(Alignment.BottomCenter))
//            }
            val navController = rememberNavController()

//            navController.addOnDestinationChangedListener { controller: NavController, destination: NavDestination, arguments: Bundle? ->
//                Log.d("sads", "OnDestinationChangedListener destination = ${destination?.route}")
//            }

            val mainContentsStateRemeber = remember {
                mutableStateOf(
                    MainContentsState(
                        state = "Hi"
                    )
                )
            }


            MainContent(
                navController = navController,
                mainContentsStateRemeber = mainContentsStateRemeber,
                items = Navs.tabs
            )
        }
    }
}


@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    navController: NavHostController,
    mainContentsStateRemeber: MutableState<MainContentsState>,
    items: List<Navs>
) {
    val stateSheet = rememberModalBottomSheetState(
        //initialValue = ModalBottomSheetValue.Hidden
    )

    //val stateMain = mainContentsStateRemeber

    val scope = rememberCoroutineScope()
    ComposePlay3Theme {
        // A surface container using the 'background' color from the theme
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
        ) {
            NavHost(
                modifier = Modifier.padding(top = 0.dp, bottom = 0.dp),
                navController = navController, startDestination = Navs.ScreenBaseMain.screenRoute
            ) {
                composable(route = Navs.ScreenBaseMain.screenRoute) {
                    ScreenBaseMain(
                        navController = navController
                    )
                }
                composable(
                    route = Navs.ScreenBasePayments.screenRoute,
                ) {
                    ScreenBasePayments(
                        navController = navController
                    )
                }
                composable(route = Navs.ScreenBaseSettings.screenRoute) {
                    ScreenBaseSettings(
                        navController = navController
                    )
                }

                composable(route = "${Navs.ScreenSecondProduct.screenRoute}/{userId}?startScreen={startScreen}") { backStackEntry ->
                    ScreenSecondProduct(
                        //viewModel = viewModel(modelClass = ScreenBoxViewModel::class.java),
                        userId = backStackEntry.arguments?.getString("userId") ?: "",
                        startScreen = backStackEntry.arguments?.getString("startScreen") ?: "",
                        navController = navController
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
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination



                val backEnabled = items.any {
                    it.screenRoute == navController.currentDestination?.route &&
                            navController.currentDestination?.route != Navs.ScreenBaseMain.screenRoute
                }
                BackHandler(enabled = backEnabled) {
                    Log.d("asdsad", "BackHandler")
                    navController.bottomNavigatonClick(Navs.ScreenBaseMain.screenRoute)
                }


                items.forEachIndexed { index, nav ->

                    val selected = navController.currentBackStack.value.any {
                        it.destination.route == nav.screenRoute
                    }

                    NavigationBarItem(
                        icon = { Icon(painterResource(id = nav.icon), null) },
                        label = { Text(text = nav.title) },
                        selected = selected,
                        onClick = {
                            navController.bottomNavigatonClick(nav.screenRoute)
                        }
                    )
                }
            }
        }
    }
}


@SuppressLint("RestrictedApi")
fun NavController?.bottomNavigatonClick(screenRoute: String) {
    val navController = this
    navController?.navigate(screenRoute) {

        val currentBaseTab = navController?.currentBackStack?.value?.get(1)?.destination
        val curTabNavDestination = navController?.graph?.findNode(
            route = screenRoute
        )

        if (Navs.isTab(screenRoute = screenRoute) && currentBaseTab != null) {
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
    val state: String
)

