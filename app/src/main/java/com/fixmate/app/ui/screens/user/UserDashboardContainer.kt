package com.fixmate.app.ui.screens.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fixmate.app.navigation.Screen

@Composable
fun UserDashboardContainer(
    onServiceClick: (Int) -> Unit,
    onLogout: () -> Unit,
    onScanClick: () -> Unit,
    onSOSClick: () -> Unit
) {
    val navController = rememberNavController()
    val items = listOf(
        Triple("Home", Screen.UserHome.route, Icons.Default.Home),
        Triple("Bookings", "bookings_list", Icons.Default.CalendarMonth),
        Triple("Alerts", "alerts", Icons.Default.Notifications),
        Triple("Profile", Screen.Profile.route, Icons.Default.Person)
    )
    
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.third, contentDescription = item.first) },
                        label = { Text(item.first) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item.second) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.UserHome.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.UserHome.route) {
                UserHomeScreen(
                    onCategoryClick = { /* demo */ onServiceClick(1) },
                    onServiceClick = onServiceClick,
                    onScanClick = onScanClick,
                    onSOSClick = onSOSClick
                )
            }
            composable("bookings_list") {
                // Reuse existing list UI or placeholder
                ServiceListScreen(category = "My Bookings", onServiceClick = {}, onBack = {})
            }
            composable("alerts") {
                // Placeholder for notifications
                ServiceListScreen(category = "Notifications", onServiceClick = {}, onBack = {})
            }
            composable(Screen.Profile.route) {
                ProfileScreen(onLogout = onLogout)
            }
        }
    }
}
