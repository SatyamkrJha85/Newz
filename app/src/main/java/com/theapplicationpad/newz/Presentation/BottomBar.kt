package com.theapplicationpad.newz.Presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SaveAs
import androidx.compose.material.icons.filled.ScreenSearchDesktop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.theapplicationpad.ViewModel.NewsViewModel
import com.theapplicationpad.newz.Navigation.Routes
import com.theapplicationpad.newz.R

@Composable
fun BottomBar(modifier: Modifier = Modifier,viewModel: NewsViewModel= hiltViewModel()) {
    val articles by viewModel.articles.collectAsState()
    val navController = rememberNavController()
    var isbottombarVisible = remember {
        mutableStateOf(true)
    }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = { 
            AnimatedVisibility(visible = isbottombarVisible.value) {
                MyBottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home.routes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Home.routes) {
                NewsListScreen(navController = navController)
                isbottombarVisible.value=true
            }
            composable(Routes.Search.routes) {
                Searchscreen(navController = navController)
                isbottombarVisible.value=true

            }
            composable(Routes.Save.routes) {

            }

            composable(
                route = Routes.Details.routes,
                arguments = listOf(navArgument("url") { type = NavType.StringType }),
                deepLinks = listOf(navDeepLink { uriPattern = "android-app://androidx.navigation/Details/{url}" })
            ) { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url")
                val article = viewModel.getArticleByUrl(url)
                article?.let {
                    DetailsScreen(article = it)
                    isbottombarVisible.value = false
                }
            }



        }
    }
}


@Composable
fun MyBottomBar(navController: NavHostController) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val (selectedRoute, setSelectedRoute) = remember { mutableStateOf(Routes.Home.routes) }

    // Define navigation items
    val homeItem = BottomNavItem(
        "Home", Routes.Home.routes,
        icon =
        Icons.Filled.Home,

        )
    val Searchitem = BottomNavItem(
        "Search", Routes.Search.routes,
        icon =
        Icons.Filled.ScreenSearchDesktop,
    )
    val Saveitem = BottomNavItem(
        "Save", Routes.Save.routes,
        icon =
        Icons.Filled.SaveAs,
    )





    Row(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // first side IconButton
        IconButton(
            onClick = {
                if (currentRoute != homeItem.route) {
                    navController.navigate(homeItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                    setSelectedRoute(homeItem.route)

                }
            },

            ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .background(

                        if (selectedRoute == homeItem.route) Color.White else Color.Gray,
                    ), contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = homeItem.title,
                    tint = if (selectedRoute == homeItem.route) Color.Black else Color.White,
                )
            }


        }

        // second side IconButton
        IconButton(
            onClick = {
                if (currentRoute != Searchitem.route) {
                    navController.navigate(Searchitem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                    setSelectedRoute(Searchitem.route)
                }
            },
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
                    .background(

                        if (selectedRoute == Searchitem.route) Color.White else Color.Gray,
                    ), contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = homeItem.title,
                    tint = if (selectedRoute == Searchitem.route) Color.Black else Color.White,
                )
            }
        }


        // third side IconButton
//        IconButton(
//            onClick = {
//                if (currentRoute != Saveitem.route) {
//                    navController.navigate(Saveitem.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                    }
//                    setSelectedRoute(Saveitem.route)
//                }
//            },
//        ) {
//            Box(
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .size(50.dp)
//                    .background(
//
//                        if (selectedRoute == Saveitem.route) Color.White else Color.Gray,
//                    ), contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    modifier = Modifier.size(25.dp),
//                    painter = painterResource(id = R.drawable.save),
//                    contentDescription = homeItem.title,
//                    tint = if (selectedRoute == Saveitem.route) Color.Black else Color.White,
//                )
//            }
//        }


    }
}

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
    val secondicon: Int = 0
)