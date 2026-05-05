package com.fixmate.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fixmate.app.ui.screens.*
import com.fixmate.app.ui.screens.admin.AdminDashboardScreen
import com.fixmate.app.ui.screens.provider.ProviderDashboardScreen
import com.fixmate.app.ui.screens.user.BookingScreen
import com.fixmate.app.ui.screens.user.ServiceDetailScreen
import com.fixmate.app.ui.screens.user.UserDashboardContainer
import com.fixmate.app.ui.screens.user.UserHomeScreen

@Composable
fun FixMateNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Splash.route,
    onRoleSaved: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.RoleSelection.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        
        composable(Screen.RoleSelection.route) {
            RoleSelectionScreen(onRoleSelected = { role ->
                onRoleSaved(role)
                when (role) {
                    "User" -> navController.navigate(Screen.UserHome.route)
                    "Provider" -> navController.navigate(Screen.ProviderDashboard.route)
                    "Admin" -> navController.navigate(Screen.AdminDashboard.route)
                }
            })
        }
        
        // User Flow
        composable(Screen.UserHome.route) {
            UserDashboardContainer(
                onServiceClick = { id -> navController.navigate(Screen.ServiceDetail.createRoute(id)) }
            )
        }
        
        composable(
            route = Screen.ServiceDetail.route,
            arguments = listOf(navArgument("serviceId") { type = NavType.IntType })
        ) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getInt("serviceId") ?: 1
            ServiceDetailScreen(
                serviceId = serviceId,
                onBack = { navController.popBackStack() },
                onBook = { navController.navigate(Screen.Booking.createRoute(serviceId)) }
            )
        }
        
        composable(
            route = Screen.Booking.route,
            arguments = listOf(navArgument("serviceId") { type = NavType.IntType })
        ) { backStackEntry ->
            val serviceId = backStackEntry.arguments?.getInt("serviceId") ?: 1
            BookingScreen(
                serviceId = serviceId,
                onBookingConfirmed = {
                    navController.popBackStack()
                }
            )
        }
        
        // Provider Flow
        composable(Screen.ProviderDashboard.route) {
            ProviderDashboardScreen()
        }
        
        // Admin Flow
        composable(Screen.AdminDashboard.route) {
            AdminDashboardScreen()
        }
    }
}
