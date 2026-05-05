package com.fixmate.app.ui.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fixmate.app.data.DummyData
import com.fixmate.app.ui.components.CustomButton
import com.fixmate.app.ui.components.CustomRatingBar

@Composable
fun ServiceDetailScreen(serviceId: Int, onBack: () -> Unit, onBook: () -> Unit) {
    val service = DummyData.services.find { it.id == serviceId } ?: DummyData.services[0]

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = Color.White) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedIconButton(onClick = {}, modifier = Modifier.size(56.dp)) {
                        Icon(Icons.Default.Chat, contentDescription = null)
                    }
                    CustomButton(
                        text = "Book Now",
                        onClick = onBook,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(service.icon, contentDescription = null, modifier = Modifier.size(100.dp), tint = MaterialTheme.colorScheme.primary)
                
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorite")
                }
            }
            
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = service.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 8.dp)) {
                    CustomRatingBar(rating = service.rating)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "(${service.rating})", color = Color.Gray)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = service.price, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                }
                
                Divider(modifier = Modifier.padding(vertical = 16.dp))
                
                Text(text = "Description", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    text = service.description,
                    modifier = Modifier.padding(vertical = 8.dp),
                    lineHeight = 22.sp,
                    color = Color.DarkGray
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(text = "Includes", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                BulletPoint("Professional equipment")
                BulletPoint("Post-service cleanup")
                BulletPoint("Insurance coverage")
            }
        }
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Box(modifier = Modifier.size(6.dp).background(MaterialTheme.colorScheme.primary, shape = androidx.compose.foundation.shape.CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 14.sp)
    }
}
