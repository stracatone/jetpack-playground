package com.example.jetpacktestapp.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpacktestapp.compose.details.DetailsScreen
import com.example.jetpacktestapp.compose.home.HomeScreen

@Composable
fun JetpackTestApp() {
    val navController = rememberNavController()
    JetpackTestNavHost(navController)
}

@Composable
fun JetpackTestNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(onItemClick = { itemId ->
                navController.navigate(Screen.Details.createRoute(itemId))
            })
        }
        composable(route = Screen.Details.route, arguments = Screen.Details.navArguments) {
            DetailsScreen(onBackClick = { navController.navigateUp() })
        }
    }
}