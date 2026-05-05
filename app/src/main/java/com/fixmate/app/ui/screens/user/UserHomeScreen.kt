package com.fixmate.app.ui.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fixmate.app.data.DummyData
import com.fixmate.app.data.Service
import com.fixmate.app.ui.components.CustomCard
import com.fixmate.app.ui.components.CustomRatingBar

/**
 * UNIT I: LazyGrid, Horizontal Scroll, Nested Scroll (simulated with Column + ScrollState)
 */

@Composable
fun UserHomeScreen(
    onCategoryClick: (String) -> Unit,
    onServiceClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar Placeholder
        HomeTopBar()
        
        // Banner
        PromotionBanner()
        
        // Categories Grid
        Text(
            text = "Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        
        // UNIT I: LazyVerticalGrid for services
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .height(240.dp)
                .padding(horizontal = 8.dp),
            userScrollEnabled = false // Nested scroll handling
        ) {
            items(DummyData.categories) { category ->
                CategoryItem(
                    name = category,
                    icon = getCategoryIcon(category),
                    onClick = { onCategoryClick(category) }
                )
            }
        }
        
        // Popular Services Horizontal Scroll
        Text(
            text = "Popular Services",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        
        // UNIT I: Horizontal Scroll
        LazyRow(
            modifier = Modifier.padding(bottom = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(DummyData.services) { service ->
                PopularServiceCard(service = service, onClick = { onServiceClick(service.id) })
            }
        }
        
        Spacer(modifier = Modifier.height(80.dp)) // Padding for bottom nav
    }
}

@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Location", fontSize = 12.sp, color = Color.Gray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(16.dp), tint = MaterialTheme.colorScheme.primary)
                Text(text = "New York, USA", fontWeight = FontWeight.Bold)
            }
        }
        IconButton(onClick = {}) {
            Icon(Icons.Default.Notifications, contentDescription = null)
        }
    }
}

@Composable
fun PromotionBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "20% OFF", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = "On your first cleaning service", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun CategoryItem(name: String, icon: ImageVector, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(60.dp),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(16.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = name, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun PopularServiceCard(service: Service, onClick: () -> Unit) {
    CustomCard(
        modifier = Modifier.width(200.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.LightGray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(service.icon, contentDescription = null, modifier = Modifier.size(48.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = service.name, fontWeight = FontWeight.Bold, maxLines = 1)
            Text(text = service.price, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
            CustomRatingBar(rating = service.rating)
        }
    }
}

fun getCategoryIcon(name: String): ImageVector {
    return when (name) {
        "Plumbing" -> Icons.Default.Plumbing
        "Electrician" -> Icons.Default.ElectricalServices
        "Cleaning" -> Icons.Default.CleaningServices
        "Carpentry" -> Icons.Default.Carpenter
        "Painting" -> Icons.Default.FormatPaint
        else -> Icons.Default.Handyman
    }
}

