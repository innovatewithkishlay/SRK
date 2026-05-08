package com.fixmate.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fixmate.app.ui.screens.OnboardingScreen
import com.fixmate.app.ui.screens.SplashScreen
import com.fixmate.app.ui.screens.admin.AdminDashboardScreen
import com.fixmate.app.ui.screens.provider.ProviderDashboardScreen
import com.fixmate.app.ui.screens.user.AIScanScreen
import com.fixmate.app.ui.screens.user.BookingScreen
import com.fixmate.app.ui.screens.user.ServiceDetailScreen
import com.fixmate.app.ui.screens.user.UserDashboardContainer
import com.fixmate.app.ui.screens.user.UserHomeScreen

import androidx.navigation.compose.navigation
import com.fixmate.app.ui.screens.auth.LoginScreen

@Composable
fun FixMateNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Splash.route,
    onRoleSaved: (String) -> Unit,
    onLogout: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }
        
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onFinished = {
                navController.navigate(Screen.RoleSelection.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            })
        }
        
        composable(Screen.RoleSelection.route) {
            RoleSelectionScreen(onRoleSelected = { role ->
                navController.navigate(Screen.Login.createRoute(role))
            })
        }
        
        composable(
            route = Screen.Login.route,
            arguments = listOf(navArgument("role") { type = NavType.StringType })
        ) { backStackEntry ->
            val role = backStackEntry.arguments?.getString("role") ?: "User"
            LoginScreen(
                role = role,
                onLoginSuccess = {
                    onRoleSaved(role)
                    when (role) {
                        "User" -> navController.navigate(Screen.UserGraph.route) {
                            popUpTo(Screen.RoleSelection.route) { inclusive = true }
                        }
                        "Provider" -> navController.navigate(Screen.ProviderGraph.route) {
                            popUpTo(Screen.RoleSelection.route) { inclusive = true }
                        }
                        "Admin" -> navController.navigate(Screen.AdminGraph.route) {
                            popUpTo(Screen.RoleSelection.route) { inclusive = true }
                        }
                    }
                },
                onSignupClick = { /* demo */ }
            )
        }
        
        // USER NESTED GRAPH
        navigation(
            startDestination = Screen.UserHome.route,
            route = Screen.UserGraph.route
        ) {
            composable(Screen.UserHome.route) {
                UserDashboardContainer(
                    onServiceClick = { id -> navController.navigate(Screen.ServiceDetail.createRoute(id)) },
                    onLogout = onLogout
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
        }
        
        // PROVIDER NESTED GRAPH
        navigation(
            startDestination = Screen.ProviderDashboard.route,
            route = Screen.ProviderGraph.route
        ) {
            composable(Screen.ProviderDashboard.route) {
                ProviderDashboardScreen(onLogout = onLogout)
            }
        }
        
        // ADMIN NESTED GRAPH
        navigation(
            startDestination = Screen.AdminDashboard.route,
            route = Screen.AdminGraph.route
        ) {
            composable(Screen.AdminDashboard.route) {
                AdminDashboardScreen(onLogout = onLogout)
            }
        }
    }
}
