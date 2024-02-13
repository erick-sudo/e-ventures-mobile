package com.eventures.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
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

    val navItems = mapOf(
        NavRoutes.Home to Icons.Outlined.Home,
        NavRoutes.Loans to ImageVector.vectorResource(R.drawable.outline_attach_money_24),
        NavRoutes.Notifications to Icons.Outlined.Notifications
    )

    NavigationBar {
        for((route, icon) in navItems ) {
            NavigationBarItem(
                selected = true,
                onClick = { /*TODO*/ },
                icon = { Icon(imageVector = icon, contentDescription = route.route) },
                label = { Text(text = route.label) }
            )
        }
    }
}