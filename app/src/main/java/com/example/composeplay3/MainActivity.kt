package com.example.composeplay3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composeplay3.ui.theme.ComposePlay3Theme

class MainActivity : ComponentActivity() {


    companion object {
        val items = listOf(
            Navs.GraphOne,
            Navs.GraphTwo,
            Navs.GraphThree
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainContent(navController = navController, items = items)
        }
    }
}


fun NavGraphBuilder.globalNavigationDestinations(navController: NavController) {
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
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(navController: NavHostController, items: List<Navs>) {
    ComposePlay3Theme {
        // A surface container using the 'background' color from the theme
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
        ) {
            NavHost(
                modifier = Modifier.padding(top = 56.dp, bottom = 56.dp),
                navController = navController, startDestination = Navs.GraphOne.screenRoute
            ) {

                navigation(
                    route = Navs.GraphOne.screenRoute,
                    startDestination = Navs.BaseOne.screenRoute
                ) {
                    composable(route = Navs.BaseOne.screenRoute) {
                        ScreenBaseOne(
                            navController = navController
                        )
                    }
                    globalNavigationDestinations(navController)
                }


                navigation(
                    route = Navs.GraphTwo.screenRoute,
                    startDestination = Navs.BaseTwo.screenRoute
                ) {
                    composable(route = Navs.BaseTwo.screenRoute) {
                        ScreenBaseTwo(
                            navController = navController
                        )
                    }
                    globalNavigationDestinations(navController)
                }


                navigation(
                    route = Navs.GraphThree.screenRoute,
                    startDestination = Navs.BaseThree.screenRoute
                ) {
                    composable(route = Navs.BaseThree.screenRoute) {
                        ScreenBaseThree(
                            navController = navController
                        )
                    }
                    globalNavigationDestinations(navController)
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
                    Icon(Icons.Filled.Search, contentDescription = "Меню")
                }
            )

            var selctedRoute: String? = null
            //Scaffold()
            BottomNavigation(
                modifier = Modifier.align(Alignment.BottomCenter),
                backgroundColor = colorResource(id = R.color.teal_200),
                contentColor = Color.Black
            ) {
//                val d by navController.currentBackStackEntryAsState()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val currentDestination = navBackStackEntry?.destination



                Log.d("sads", "currentDestination = ${currentDestination?.route}")
                val hierarchy = currentDestination?.hierarchy


                for (navGraphDestination in navController.graph) {
                    Log.d("sads", "navGraphDestination = ${navGraphDestination?.route}")

                }

                if (hierarchy != null) {
                    for (dest in hierarchy) {
                        Log.d("sads", "hierarchy dest = ${dest?.route}")
                    }
                }


                for (navBackStackEntryBack in navController.backQueue) {
                    val startScreen = navBackStackEntryBack.arguments?.getString("startScreen")
                    val simpleClass = navBackStackEntryBack.destination::class.java.simpleName
                    Log.d(
                        "sads",
                        "navBackStackEntryBack.destination=${navBackStackEntryBack.destination.route} simpleClass=$simpleClass  startScreen=$startScreen"
                    )
                }

//                while (currentDestination?.hierarchy?.iterator()?.hasNext() == true) {
//                    val dest = currentDestination?.hierarchy?.iterator()?.next()
//
//                    Log.d("sads", "dest = ${dest?.route}")
//                }


//                val backEnabled = items.any {
//                    it.screenRoute == navController.currentDestination?.route &&
//                            navController.currentDestination?.route != Navs.BaseOne.screenRoute
//                }
//                BackHandler(enabled = backEnabled) {
//                    Log.d("asdsad", "BackHandler")
//                    navController.graph.findStartDestination().route?.let {route ->
//                        navController.navigate(route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//
//                }


                items.forEachIndexed { index, navs ->

//                    var selected = false
//                    if (navs.screenRoute == navController.graph.findStartDestination().route) {
//                        val nonFirstTabsRoutes = items.subList(1, items.size).map {
//                            it.screenRoute
//                        }
//                        selected = navController.backQueue.none {
//                            nonFirstTabsRoutes.contains(it.destination.route)
//                        }
//                    } else {
//                        selected = navController.backQueue.any {
//                            it.destination.route == navs.screenRoute
//                        }
//                    }

                    Log.d(
                        "sads",
                        "navController.graph.findStartDestination()=${navController.graph.findStartDestination().route} navController.graph=${navController.graph} navController.graph.startDestinationRoute=${navController.graph.startDestinationRoute}"
                    )

                    BottomNavigationItem(
                        icon = { Icon(painterResource(id = navs.icon), null) },
                        label = { Text(text = navs.title) },
                        //selected = currentDestination?.route == navs.screenRoute,
                        selected = currentDestination?.hierarchy?.any { it.route == navs.screenRoute } == true,
//                        selected = selected,
                        selectedContentColor = Color.Red,
                        unselectedContentColor = Color.Black,
                        onClick = {

//                            var currentTab: NavDestination? = null
//
//                            val nonFirstTabsDestinations = items.subList(1, items.size)
//
//                            nonFirstTabsDestinations.forEach { navsInner ->
//                                if (currentTab == null) {
//                                    currentTab =
//                                        (navController.backQueue.firstOrNull() { it.destination.route == navsInner.screenRoute })?.destination
//                                }
//                            }
//
//                            if (currentTab == null) {
//                                currentTab = navController.graph.findStartDestination()
//                            }
//
//
//                            navController.backQueue.forEach {
//                                Log.d("sads", "it = $it")
//                            }

                            navController.navigate(navs.screenRoute) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items

//                                popUpTo(currentTab!!.id) {
//                                    saveState = true
//                                    inclusive = true
//                                }


                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }


                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
//                                // We want to reset the graph if it is clicked while already selected
//                                restoreState = tab != currentTab
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MyBottomNavigation() {
    Row() {
        //BottomMenuItem
    }
}

@Composable
fun Greeting(name1: String, name2: String) {
    Row {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colorScheme.secondary, RectangleShape)
                .clickable { Log.d("dgty", "clicked") }
                .background(MaterialTheme.colorScheme.primary, RectangleShape)
                .padding(16.dp),
            text = "$name1",
            style = TextStyle(
                color = Color.Red
            )
        )

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colorScheme.secondary, RectangleShape)
                .background(MaterialTheme.colorScheme.primary, RectangleShape)
                .padding(16.dp),
            text = "$name2",
            style = TextStyle(
                color = Color.Red
            )
        )
        ScreenConstraint("Android", "ios")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent(navController = rememberNavController(), items = MainActivity.items)
}