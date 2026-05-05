package com.fixmate.app.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fixmate.app.ui.components.CustomCard
import kotlinx.coroutines.launch

/**
 * UNIT VI: Navigation Drawer implementation
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(onLogout: () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    var selectedCategory by remember { mutableStateOf("Active") }
    val categories = listOf("Active", "Pending Approval", "Flagged")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text("FixMate Admin", modifier = Modifier.padding(16.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                
                NavigationDrawerItem(
                    label = { Text("Dashboard") },
                    selected = true,
                    onClick = { scope.launch { drawerState.close() } },
                    icon = { Icon(Icons.Default.Dashboard, contentDescription = null) }
                )
                
                // Nested Management Items
                Text("Management", modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp), fontSize = 12.sp, color = Color.Gray)
                
                NavigationDrawerItem(
                    label = { Text("Customers") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.padding(start = 16.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Service Providers") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close() } },
                    icon = { Icon(Icons.Default.Engineering, contentDescription = null) },
                    modifier = Modifier.padding(start = 16.dp)
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                NavigationDrawerItem(
                    label = { Text("Logout") },
                    selected = false,
                    onClick = { 
                        scope.launch { 
                            drawerState.close()
                            onLogout()
                        } 
                    },
                    icon = { Icon(Icons.Default.Logout, contentDescription = null) }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Admin Panel") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Admin Stat Cards with Canvas charts (Simple lines)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    AdminStatCardWithChart("Total Revenue", "$54,200", Color(0xFF4CAF50), Modifier.weight(1f))
                    AdminStatCardWithChart("User Growth", "+12.5%", Color(0xFF2196F3), Modifier.weight(1f))
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Categorized Provider Management
                Text(text = "Provider Management", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                
                ScrollableTabRow(
                    selectedTabIndex = categories.indexOf(selectedCategory),
                    containerColor = Color.Transparent,
                    edgePadding = 0.dp,
                    divider = {}
                ) {
                    categories.forEach { category ->
                        Tab(
                            selected = selectedCategory == category,
                            onClick = { selectedCategory = category },
                            text = { Text(category) }
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(5) { index ->
                        ProviderManagementItem(name = "Provider #$index", status = selectedCategory)
                    }
                }
            }
        }
    }
}

@Composable
fun AdminStatCardWithChart(title: String, value: String, chartColor: Color, modifier: Modifier = Modifier) {
    CustomCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontSize = 14.sp, color = Color.Gray)
            Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            
            // Simple Canvas Chart
            androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxWidth().height(40.dp).padding(top = 8.dp)) {
                val path = androidx.compose.ui.graphics.Path()
                path.moveTo(0f, size.height)
                path.cubicTo(size.width * 0.3f, size.height * 0.2f, size.width * 0.6f, size.height * 0.8f, size.width, size.height * 0.4f)
                drawPath(path, color = chartColor, style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx()))
            }
        }
    }
}

@Composable
fun ProviderManagementItem(name: String, status: String) {
    CustomCard {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(modifier = Modifier.size(40.dp), shape = CircleShape, color = Color.LightGray) {
                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.padding(8.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, fontWeight = FontWeight.Bold)
                Text(text = "Electrical Expert", fontSize = 12.sp, color = Color.Gray)
            }
            Badge(
                containerColor = when(status) {
                    "Active" -> Color(0xFFE8F5E9)
                    "Flagged" -> Color(0xFFFFEBEE)
                    else -> Color(0xFFE3F2FD)
                },
                contentColor = when(status) {
                    "Active" -> Color(0xFF2E7D32)
                    "Flagged" -> Color(0xFFC62828)
                    else -> Color(0xFF1565C0)
                }
            ) {
                Text(status, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
            }
        }
    }
}
