package com.fixmate.app.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object RoleSelection : Screen("role_selection")
    
    // User Screens
    object UserHome : Screen("user_home")
    object ServiceList : Screen("service_list/{category}") {
        fun createRoute(category: String) = "service_list/$category"
    }
    object ServiceDetail : Screen("service_detail/{serviceId}") {
        fun createRoute(serviceId: Int) = "service_detail/$serviceId"
    }
    object Booking : Screen("booking/{serviceId}") {
        fun createRoute(serviceId: Int) = "booking/$serviceId"
    }
    object Profile : Screen("profile")
    object Map : Screen("map")
    object Chat : Screen("chat")
    
    // Provider Screens
    object ProviderDashboard : Screen("provider_dashboard")
    object ProviderJobs : Screen("provider_jobs")
    
    // Admin Screens
    object AdminDashboard : Screen("admin_dashboard")
    object AdminManageUsers : Screen("admin_manage_users")
}
