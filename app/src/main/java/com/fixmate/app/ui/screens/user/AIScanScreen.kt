package com.fixmate.app.ui.screens.user

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIScanScreen(onBack: () -> Unit, onServiceDetected: (Int) -> Unit) {
    var isScanning by remember { mutableStateOf(true) }
    var showResult by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(4000) // Simulate scanning for 4 seconds
        isScanning = false
        showResult = true
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Simulated Camera Viewfinder
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
                .background(Color.DarkGray.copy(alpha = 0.5f))
        ) {
            // Scanner Animation Line
            if (isScanning) {
                val infiniteTransition = rememberInfiniteTransition(label = "scanner")
                val yOffset by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(2000, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "yOffset"
                )

                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .offset(y = maxHeight * yOffset)
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color(0xFF0EA5E9), Color.Transparent)
                                )
                            )
                            .shadow(8.dp, spotColor = Color(0xFF0EA5E9))
                    )
                }
                
                Text(
                    text = "AI Analyzing Problem...",
                    modifier = Modifier.align(Alignment.Center).padding(top = 100.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        // Top Controls
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack, modifier = Modifier.background(Color.Black.copy(alpha = 0.5f), CircleShape)) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }
            IconButton(onClick = {}, modifier = Modifier.background(Color.Black.copy(alpha = 0.5f), CircleShape)) {
                Icon(Icons.Default.FlashOn, contentDescription = "Flash", tint = Color.White)
            }
        }

        // Bottom Controls (Camera Button)
        if (isScanning) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            ) {
                Surface(
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(4.dp, Color.Gray)
                ) {
                    Icon(
                        Icons.Default.PhotoCamera,
                        contentDescription = "Scan",
                        modifier = Modifier.padding(20.dp),
                        tint = Color.Black
                    )
                }
            }
        }

        // Result Bottom Sheet
        if (showResult) {
            ModalBottomSheet(
                onDismissRequest = { showResult = false },
                containerColor = Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).padding(bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Diagnosis Complete!",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "AI Confidence: 98.4%",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Detected Problem:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("Visible pipe corrosion and active leakage in kitchen secondary line.", fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(12.dp))
                            Text("Recommended Service:", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("Advanced Plumbing & Pipe Repair", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Button(
                        onClick = { onServiceDetected(2) }, // Redirect to Plumbing service
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Book Recommended Expert ($40-$60)")
                    }
                    
                    TextButton(onClick = { showResult = false }) {
                        Text("Retake Photo", color = Color.Gray)
                    }
                }
            }
        }
    }
}
