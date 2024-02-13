package com.eventures.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import com.eventures.R
import com.eventures.screens.NavRoutes

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {


    NavigationBar {

        val navItems = mapOf(
            NavRoutes.Home to Icons.Outlined.Home,
            NavRoutes.Loans to ImageVector.vectorResource(R.drawable.outline_attach_money_24)
        )

        navItems.forEach {  navRoute, icon ->
            //NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = { /*TODO*/ })
        }
    }
}