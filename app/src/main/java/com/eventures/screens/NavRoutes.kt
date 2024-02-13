package com.eventures.screens

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

sealed class NavRoutes (
    val route: String,
    val label: String
) {
    data object Home: NavRoutes("home", "Home")
    data object Loans: NavRoutes("loans", "Loans")
}

object UseNavigate {

    fun navigate(
        navHostController: NavHostController,
        destination: String
    ) {
        navHostController.navigate(destination) {
            navigationStateRestoration(navHostController)
        }
    }

    fun navigate(
        navHostController: NavHostController,
        destination: NavRoutes
    ) {
        navHostController.navigate(destination.route) {
            navigationStateRestoration(navHostController)
        }
    }

    private fun NavOptionsBuilder.navigationStateRestoration(navHostController: NavHostController) {
        popUpTo(navHostController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}