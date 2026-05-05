package com.fixmate.app.ui.screens.provider

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fixmate.app.data.DummyData
import com.fixmate.app.data.JobRequest
import com.fixmate.app.ui.components.CustomCard
import kotlinx.coroutines.launch

/**
 * UNIT VI: TabRow + HorizontalPager
 */

@Composable
fun ProviderDashboardScreen(onLogout: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val scope = rememberCoroutineScope()
    val tabs = listOf("Pending Jobs", "Completed Jobs")

    Column(modifier = Modifier.fillMaxSize()) {
        ProviderHeader(onLogout = onLogout)
        
        // UNIT VI: TabRow
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(title) }
                )
            }
        }
        
        // UNIT VI: HorizontalPager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> JobList(DummyData.jobRequests.filter { it.status == "Pending" })
                1 -> JobList(DummyData.jobRequests.filter { it.status == "Completed" })
            }
        }
    }
}

@Composable
fun ProviderHeader(onLogout: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Hello, Mike!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Pro Electrician", color = MaterialTheme.colorScheme.primary, fontSize = 14.sp)
        }
        IconButton(onClick = onLogout) {
            Icon(Icons.Default.Logout, contentDescription = "Logout", tint = Color.Red)
        }
    }
    
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        StatCard("Total Jobs", "24", Modifier.weight(1f))
        StatCard("Earnings", "$1,200", Modifier.weight(1f))
        StatCard("Rating", "4.9", Modifier.weight(1f))
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun StatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = title, fontSize = 12.sp)
        }
    }
}

@Composable
fun JobList(jobs: List<JobRequest>) {
    // UNIT I: LazyColumn
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(jobs) { job ->
            JobCard(job = job)
        }
    }
}

@Composable
fun JobCard(job: JobRequest) {
    CustomCard {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = job.serviceType, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = job.time, fontSize = 12.sp, color = Color.Gray)
            }
            
            // Skill Tags
            Row(modifier = Modifier.padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(onClick = {}, label = { Text("Urgent") }, colors = AssistChipDefaults.assistChipColors(containerColor = Color(0xFFFFEBEE)))
                AssistChip(onClick = {}, label = { Text("High Priority") })
            }
            
            Text(text = "Client: ${job.userName}", fontSize = 14.sp)
            Text(text = "Address: ${job.address}", fontSize = 14.sp)
            
            if (job.status == "Pending") {
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { /* Accept */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null)
                        Text("Accept")
                    }
                    Button(
                        onClick = { /* Reject */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null)
                        Text("Reject")
                    }
                }
            }
        }
    }
}
