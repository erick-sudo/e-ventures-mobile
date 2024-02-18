package com.eventures.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eventures.R
import com.eventures.screens.NavRoutes
import com.eventures.screens.UseNavigate

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val backStackEntry by navHostController.currentBackStackEntryAsState()

    val navItems = listOf(
        NavRoutes.Home to Icons.Outlined.Home,
        NavRoutes.Loans to ImageVector.vectorResource(R.drawable.outline_attach_money_24),
        NavRoutes.Notifications to Icons.Outlined.Notifications
    )

    NavigationBar(

    ) {
        navItems.forEach { (route, imageVector) ->
            NavigationBarItem(
                selected = backStackEntry?.destination?.route == route.route,
                onClick = { UseNavigate.navigate(navHostController, route) },
                icon = {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = route.route,
                    )
                }
            )
        }
    }
}

@Composable
fun UnderlinedNavRail(
    modifier: Modifier = Modifier,
    onChange: (Int) -> Unit,
    items: List<@Composable (MutableIntState) -> Unit>
) {

    val selected = remember {
        mutableIntStateOf(0)
    }

    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .then(modifier)
    ) {
        items.forEachIndexed { index, action ->
            Column(
                modifier = Modifier
                    .weight(1f / items.size)
                    .clickable {
                        selected.intValue = index
                        onChange(index)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                action(selected)
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(
                            color = if (selected.intValue == index) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
            }
        }
    }
}