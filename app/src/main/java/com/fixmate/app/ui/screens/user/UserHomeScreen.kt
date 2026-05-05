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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
    val sheetState = rememberModalBottomSheetState()
    var showFilterSheet by remember { mutableStateOf(false) }
    val primaryColor = MaterialTheme.colorScheme.primary

    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFilterSheet = false },
            sheetState = sheetState
        ) {
            FilterContent(onApply = { showFilterSheet = false })
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Half-sided modern color accent
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
            shape = RoundedCornerShape(bottomEnd = 100.dp)
        ) {}

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar with Filter Button
            HomeTopBar(onFilterClick = { showFilterSheet = true })
            
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
}

@Composable
fun HomeTopBar(onFilterClick: () -> Unit) {
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
        IconButton(onClick = onFilterClick) {
            Icon(Icons.Default.FilterList, contentDescription = "Filter")
        }
        IconButton(onClick = {}) {
            Icon(Icons.Default.Notifications, contentDescription = null)
        }
    }
}

@Composable
fun FilterContent(onApply: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp).padding(bottom = 32.dp)) {
        Text("Filter Services", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Price Range", fontWeight = FontWeight.Medium)
        var sliderPosition by remember { mutableStateOf(0f..100f) }
        RangeSlider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..200f
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("$${sliderPosition.start.toInt()}")
            Text("$${sliderPosition.endInclusive.toInt()}")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("Rating", fontWeight = FontWeight.Medium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            (1..5).forEach { rating ->
                FilterChip(
                    selected = rating == 4,
                    onClick = { },
                    label = { Text("$rating★") }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = onApply, modifier = Modifier.fillMaxWidth()) {
            Text("Apply Filters")
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
        modifier = Modifier.width(220.dp),
        onClick = onClick
    ) {
        Column {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                placeholder = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_menu_gallery),
                error = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_menu_report_image)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = service.name, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(text = service.price, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
                CustomRatingBar(rating = service.rating)
            }
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

