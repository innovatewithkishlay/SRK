package com.fixmate.app.ui.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MapScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Fake Map UI
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE2E8F0))
        ) {
            // Simulated grid/lines for map
            Column {
                repeat(20) {
                    Divider(color = Color.White.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
        
        // Map Markers
        Icon(
            Icons.Default.LocationOn,
            contentDescription = null,
            modifier = Modifier.size(40.dp).align(Alignment.Center).offset(y = (-40).dp),
            tint = Color.Red
        )
        
        Icon(
            Icons.Default.LocationOn,
            contentDescription = null,
            modifier = Modifier.size(40.dp).align(Alignment.Center).offset(x = 100.dp, y = 50.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        
        // Bottom Sheet Placeholder
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Nearby Providers", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(modifier = Modifier.size(40.dp), shape = MaterialTheme.shapes.small, color = MaterialTheme.colorScheme.primaryContainer) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.padding(8.dp))
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(text = "Pro Plumbing Services", fontWeight = FontWeight.Medium)
                        Text(text = "0.5 miles away", fontSize = 12.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = {}) {
                        Text("View")
                    }
                }
            }
        }
    }
}
