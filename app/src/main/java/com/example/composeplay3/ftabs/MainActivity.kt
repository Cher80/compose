package com.example.composeplay3.ftabs

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState

import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeplay3.R
import com.example.composeplay3.fbannerwidth.BannerState
import com.example.composeplay3.ui.theme.ComposePlay3Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    companion object {
        val items = listOf(
            Navs.BaseOne,
            Navs.BaseTwo,
            Navs.BaseThree
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContent {
            val navController = rememberNavController()

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
                items = items
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    navController: NavHostController,
    mainContentsStateRemeber: MutableState<MainContentsState>,
    items: List<Navs>
) {
    val stateSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    //val stateMain = mainContentsStateRemeber

    val scope = rememberCoroutineScope()
    ComposePlay3Theme {
        ModalBottomSheetLayout(
            sheetState = stateSheet,
            sheetContent = {
                SheetOne(userId = "dd", startScreen = "dddf", navController = navController)
            }
        ) {
            BackHandler(enabled = stateSheet.isVisible) {
                scope.launch {
                    stateSheet.hide()
                }
            }
            // A surface container using the 'background' color from the theme
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {
                NavHost(
                    modifier = Modifier.padding(top = 56.dp, bottom = 56.dp),
                    navController = navController, startDestination = Navs.BaseOne.screenRoute
                ) {
                    composable(route = "${Navs.Box.screenRoute}/{userId}?startScreen={startScreen}") { backStackEntry ->
                        ScreenBox(
                            userId = backStackEntry.arguments?.getString("userId") ?: "",
                            startScreen = backStackEntry.arguments?.getString("startScreen") ?: "",
                            navController = navController
                        )
                    }
                    composable(route = "${Navs.Constraint.screenRoute}/{userId}?startScreen={startScreen}") { backStackEntry ->
                        ScreenConstraint(
                            userId = backStackEntry.arguments?.getString("userId") ?: "",
                            startScreen = backStackEntry.arguments?.getString("startScreen") ?: "",
                            navController = navController
                        )
                    }
                    composable(route = "${Navs.Row.screenRoute}/{userId}?startScreen={startScreen}") { backStackEntry ->
                        ScreenRow(
                            userId = backStackEntry.arguments?.getString("userId") ?: "",
                            startScreen = backStackEntry.arguments?.getString("startScreen") ?: "",
                            navController = navController
                        )
                    }
                    composable(route = "${Navs.Col.screenRoute}/{userId}?startScreen={startScreen}") { backStackEntry ->
                        ScreenColumn(
                            userId = backStackEntry.arguments?.getString("userId") ?: "",
                            startScreen = backStackEntry.arguments?.getString("startScreen") ?: "",
                            navController = navController
                        )
                    }
                    composable(route = Navs.BaseOne.screenRoute) {
                        ScreenBaseOne(
                            navController = navController
                        )
                    }
                    composable(route = Navs.BaseTwo.screenRoute) {
                        ScreenBaseTwo(
                            navController = navController
                        )
                    }
                    composable(route = Navs.BaseThree.screenRoute) {
                        ScreenBaseThree(
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

                TopAppBar(
                    navigationIcon = {
                        Icon(Icons.Filled.Menu, contentDescription = "Меню")
                    },
                    title = {
                        Text("METANIT.COM", fontSize = 22.sp)
                    },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                Log.d("mmeme", "show")
                                stateSheet.show()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Меню",
                            )
                        }
                    }
                )

                BottomNavigation(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    backgroundColor = colorResource(id = R.color.teal_200),
                    contentColor = Color.Black
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    Log.d("sads", "currentDestination = ${currentDestination?.route}")
                    val hierarchy = currentDestination?.hierarchy
                    if (hierarchy != null) {
                        for (dest in hierarchy) {
                            Log.d("sads", "hierarchy = ${dest?.route}")
                        }
                    }

                    for (navBack in navController.backQueue) {
                        Log.d("sads", "navBack = ${navBack.destination.route}")
                    }

                    val backEnabled = items.any {
                        it.screenRoute == navController.currentDestination?.route &&
                                navController.currentDestination?.route != Navs.BaseOne.screenRoute
                    }
                    BackHandler(enabled = backEnabled) {
                        Log.d("asdsad", "BackHandler")

                        bottomNavigatonClick(
                            navController = navController,
                            nav = Navs.BaseOne
                        )
                    }


                    items.forEachIndexed { index, nav ->

                        val selected = navController.backQueue.any {
                            it.destination.route == nav.screenRoute
                        }

                        BottomNavigationItem(
                            icon = { Icon(painterResource(id = nav.icon), null) },
                            label = { Text(text = nav.title) },
                            selected = selected,
                            selectedContentColor = Color.Red,
                            unselectedContentColor = Color.Black,
                            onClick = {
                                bottomNavigatonClick(
                                    navController = navController,
                                    nav = nav
                                )
                            }
                        )
                    }
                }
            }
            
            Text(text = mainContentsStateRemeber.value.state)
        }
    }
}


fun bottomNavigatonClick(navController: NavController, nav: Navs) {
    val currentBaseTab: NavDestination = navController.backQueue[1].destination

    navController.navigate(nav.screenRoute) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(currentBaseTab.id) {
            saveState = true
            inclusive = true
        }

        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}


data class MainContentsState(
    val state: String
)

