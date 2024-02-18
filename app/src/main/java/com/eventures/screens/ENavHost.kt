package com.eventures.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eventures.viewmodels.LoginViewModel

@Composable
fun ENavHost(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel
) {

    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.Home.route
    ) {

        composable(NavRoutes.Home.route) {
            Home(
                navHostController = navHostController,
                loginViewModel = loginViewModel
            )
        }
        
        composable(NavRoutes.Loans.route) {
            Loans(
                navHostController = navHostController
            )
        }

        composable(NavRoutes.Loans.route + "/makePayment/{loanId}") {backStackEntry ->
            val loanId = backStackEntry.arguments?.getString("loanId") ?: ""

            LoanRepaymentScreen(
                navHostController = navHostController,
                loanId = loanId
            )
        }

        composable(NavRoutes.Notifications.route) {
            Notifications(
                navHostController = navHostController
            )
        }
    }
}