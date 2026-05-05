package com.fixmate.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fixmate.app.ui.components.CustomCard

@Composable
fun RoleSelectionScreen(onRoleSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to FixMate",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Select your role to continue",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )

        RoleCard(
            title = "User",
            description = "Book services for your home",
            icon = Icons.Default.Person,
            onClick = { onRoleSelected("User") }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        RoleCard(
            title = "Service Provider",
            description = "Provide expert services",
            icon = Icons.Default.Engineering,
            onClick = { onRoleSelected("Provider") }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        RoleCard(
            title = "Admin",
            description = "Manage users and providers",
            icon = Icons.Default.AdminPanelSettings,
            onClick = { onRoleSelected("Admin") }
        )
    }
}

@Composable
fun RoleCard(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    CustomCard(onClick = onClick) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = description, fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}
