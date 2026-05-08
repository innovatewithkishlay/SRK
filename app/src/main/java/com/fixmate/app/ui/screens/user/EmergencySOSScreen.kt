package com.fixmate.app.ui.screens.user

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EmergencySOSScreen(onBack: () -> Unit) {
    var isTriggered by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isTriggered) Color(0xFFB91C1C) else MaterialTheme.colorScheme.background)
    ) {
        if (!isTriggered) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Icon(
                    Icons.Default.Warning,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    tint = Color(0xFFB91C1C)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "Emergency SOS",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB91C1C)
                )
                
                Text(
                    text = "Press and hold the button for 2 seconds to alert nearby emergency experts.",
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
                )
                
                Spacer(modifier = Modifier.height(48.dp))

                // SOS Pulse Button
                Box(contentAlignment = Alignment.Center) {
                    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                    val scale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.2f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "scale"
                    )

                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .scale(scale)
                            .background(Color(0xFFB91C1C).copy(alpha = 0.2f), CircleShape)
                    )

                    Surface(
                        modifier = Modifier
                            .size(160.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        val job = scope.launch {
                                            val startTime = System.currentTimeMillis()
                                            while (progress < 1f) {
                                                progress = (System.currentTimeMillis() - startTime) / 2000f
                                                delay(16)
                                            }
                                            isTriggered = true
                                        }
                                        tryAwaitRelease()
                                        job.cancel()
                                        if (!isTriggered) progress = 0f
                                    }
                                )
                            },
                        shape = CircleShape,
                        color = Color(0xFFB91C1C),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            if (progress > 0f) {
                                CircularProgressIndicator(
                                    progress = { progress },
                                    modifier = Modifier.size(160.dp),
                                    color = Color.White,
                                    strokeWidth = 8.dp,
                                    trackColor = Color.Transparent
                                )
                            }
                            Text(
                                text = "SOS",
                                color = Color.White,
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
            }
        } else {
            // Triggered State
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "EMERGENCY ALERT ACTIVE",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Finding nearest 3 experts in your area...",
                    color = Color.White.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(48.dp))
                
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )
                
                Spacer(modifier = Modifier.height(64.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        EmergencyContactItem("Nearest Expert: Mike (Plumber)", "2.4 km away")
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                        EmergencyContactItem("Back-up Expert: Sarah (Electrician)", "3.1 km away")
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel Emergency")
                }
            }
        }
    }
}

@Composable
fun EmergencyContactItem(name: String, distance: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(text = distance, fontSize = 12.sp, color = Color.Gray)
        }
        IconButton(onClick = {}) {
            Icon(Icons.Default.Call, contentDescription = "Call", tint = Color(0xFF16A34A))
        }
    }
}
