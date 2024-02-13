package com.eventures.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ENavHost(
    navHostController: NavHostController
) {

    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.Home.route
    ) {

        composable(NavRoutes.Home.route) {
            Home(
                navHostController = navHostController
            )
        }
        
        composable(NavRoutes.Loans.route) {
            Loans(
                navHostController = navHostController
            )
        }
    }
}