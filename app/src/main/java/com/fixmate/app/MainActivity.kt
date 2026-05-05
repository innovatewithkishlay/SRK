package com.fixmate.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fixmate.app.data.DataStoreManager
import com.fixmate.app.navigation.FixMateNavGraph
import com.fixmate.app.ui.theme.FixMateTheme
import com.fixmate.app.utils.NotificationHelper
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // UNIT II: Create Notification Channel
        NotificationHelper.createNotificationChannel(this)
        
        val dataStoreManager = DataStoreManager(this)
        
        setContent {
            FixMateTheme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FixMateNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        onRoleSaved = { role ->
                            scope.launch {
                                dataStoreManager.saveRole(role)
                                // UNIT II: Show Notification on role selection
                                NotificationHelper.showBookingNotification(
                                    this@MainActivity,
                                    "Welcome to FixMate",
                                    "You are now logged in as $role"
                                )
                                
                                // UNIT III: Schedule dummy reminder
                                com.fixmate.app.utils.AlarmHelper.scheduleReminder(this@MainActivity)
                            }
                        },
                        onLogout = {
                            scope.launch {
                                dataStoreManager.saveRole("") // Clear role
                                navController.navigate(Screen.RoleSelection.route) {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
