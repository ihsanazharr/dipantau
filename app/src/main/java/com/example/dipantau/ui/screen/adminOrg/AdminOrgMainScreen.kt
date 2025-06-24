package com.example.dipantau.ui.screen.adminOrg

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.runtime.remember
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AdminOrgMainScreen() {
    val navItems = listOf(
        SuperAdminNavItem.Dashboard,
        SuperAdminNavItem.Himpunan,
        SuperAdminNavItem.Admin,
        SuperAdminNavItem.Pengaturan
    )

    val navController = rememberNavController()
    val colorScheme = MaterialTheme.colorScheme

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val routesWithoutBottomBar = listOf(
        "detail_data_pengguna",
        "detail_log_aktivitas",
        "himpunan/add",
        "himpunan/edit/{himpunanId}"
    )

    val shouldShowBottomBar = remember(currentRoute) {
        derivedStateOf {
            currentRoute !in routesWithoutBottomBar
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = colorScheme.background,
            contentColor = colorScheme.onBackground,
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                SuperAdminNavGraph(navController = navController)
            }
        }
        if (shouldShowBottomBar.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .shadow(12.dp, RoundedCornerShape(24.dp)),
                    color = colorScheme.onPrimary
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        navItems.forEach { item ->
                            val isSelected = currentRoute == item.title.lowercase()

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clickable {
                                        navController.navigate(item.title.lowercase()) {
                                            // Hindari penumpukan di backstack
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.title,
                                        tint = if (isSelected)
                                            colorScheme.primary
                                        else
                                            colorScheme.onSurface
                                    )
                                    Text(
                                        text = item.title,
                                        color = if (isSelected)
                                            colorScheme.primary
                                        else
                                            colorScheme.onSurface,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                            }

                            if (item != navItems.last()) {
                                Divider(
                                    modifier = Modifier
                                        .fillMaxHeight(0.5f)
                                        .width(1.dp),
                                    color = colorScheme.outline.copy(alpha = 0.4f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class SuperAdminNavItem(val title: String, val icon: ImageVector) {
    object Dashboard : SuperAdminNavItem("Dashboard", Icons.Default.DateRange)
    object Himpunan : SuperAdminNavItem("Himpunan", Icons.Default.Favorite)
    object Admin : SuperAdminNavItem("Admin", Icons.Default.Person)
    object Pengaturan : SuperAdminNavItem("Pengaturan", Icons.Default.Settings)
}