package com.eventures.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eventures.ui.BottomNavigationBar
import com.eventures.viewmodels.LoginViewModel

@Composable
fun MainScreen(
    loginViewModel: LoginViewModel = viewModel()
) {

    val loginUiState by loginViewModel.loginUiStateFlow.collectAsState()

    val navHostController = rememberNavController()


    //val backStackEntry by navHostController.currentBackStackEntryAsState()

    if(loginUiState.status > 0) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    navHostController = navHostController
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                ENavHost(
                    navHostController,
                    loginViewModel
                )
            }
        }
    } else if(loginUiState.status == 0) {
        LandingPage(
            loginViewModel = loginViewModel
        )
    } else {
        Login(
            loginViewModel = loginViewModel
        )
    }
}