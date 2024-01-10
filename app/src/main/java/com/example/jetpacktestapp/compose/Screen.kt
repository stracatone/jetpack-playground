package com.example.jetpacktestapp.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val navArguments: List<NamedNavArgument> = emptyList()) {

    data object Home : Screen("home")

    data object Details :
        Screen(route = "details/{itemId}", navArguments = listOf(navArgument("itemId") {
            type = NavType.StringType
        })) {
        fun createRoute(itemId: String) = "details/$itemId"
    }
}