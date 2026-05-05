package com.fixmate.app.ui.screens.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fixmate.app.data.DummyData
import com.fixmate.app.data.Service
import com.fixmate.app.ui.components.CustomCard
import com.fixmate.app.ui.components.CustomRatingBar

/**
 * UNIT I: LazyColumn implementation
 */

@Composable
fun ServiceListScreen(category: String, onServiceClick: (Int) -> Unit, onBack: () -> Unit) {
    val services = DummyData.services.filter { it.category == category }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(category) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search in $category") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = MaterialTheme.shapes.medium
            )
            
            // UNIT I: LazyColumn
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(services.filter { it.name.contains(searchQuery, ignoreCase = true) }) { service ->
                    ServiceItem(service = service, onClick = { onServiceClick(service.id) })
                }
            }
        }
    }
}

@Composable
fun ServiceItem(service: Service, onClick: () -> Unit) {
    CustomCard(onClick = onClick) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier.size(80.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Icon(
                    imageVector = service.icon,
                    contentDescription = null,
                    modifier = Modifier.padding(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = service.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = service.price, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
                CustomRatingBar(rating = service.rating)
            }
        }
    }
}
