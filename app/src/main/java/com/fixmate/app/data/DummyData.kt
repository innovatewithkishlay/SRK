package com.fixmate.app.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

data class Service(
    val id: Int,
    val name: String,
    val icon: ImageVector,
    val category: String,
    val rating: Float,
    val price: String,
    val description: String
)

data class Booking(
    val id: Int,
    val serviceName: String,
    val providerName: String,
    val date: String,
    val time: String,
    val status: String, // Pending, Completed, Rejected
    val price: String
)

data class JobRequest(
    val id: Int,
    val userName: String,
    val serviceType: String,
    val address: String,
    val time: String,
    val status: String
)

object DummyData {
    val categories = listOf("Plumbing", "Electrician", "Cleaning", "Carpentry", "Painting")
    
    val services = listOf(
        Service(1, "Deep House Cleaning", Icons.Default.CleaningServices, "Cleaning", 4.8f, "$50/hr", "Professional deep cleaning for your entire home.", "https://images.unsplash.com/photo-1581578731548-c64695cc6958?q=80&w=400"),
        Service(2, "Kitchen Pipe Repair", Icons.Default.Plumbing, "Plumbing", 4.5f, "$40/hr", "Fixing leaks and unclogging kitchen pipes.", "https://images.unsplash.com/photo-1504148455328-c376907d081c?q=80&w=400"),
        Service(3, "Full Home Painting", Icons.Default.FormatPaint, "Painting", 4.9f, "$200/room", "High-quality painting with premium finishes.", "https://images.unsplash.com/photo-1589939705384-5185137a7f0f?q=80&w=400"),
        Service(4, "Electrical Wiring", Icons.Default.ElectricalServices, "Electrician", 4.7f, "$60/hr", "Safe and reliable electrical wiring services.", "https://images.unsplash.com/photo-1621905251189-08b45d6a269e?q=80&w=400"),
        Service(5, "Furniture Assembly", Icons.Default.Carpenter, "Carpentry", 4.6f, "$35/hr", "Expert assembly for all types of furniture.", "https://images.unsplash.com/photo-1595428774223-ef52624120d2?q=80&w=400")
    )
    
    val bookings = listOf(
        Booking(1, "Deep House Cleaning", "Raj", "2024-05-10", "10:00 AM", "Completed", "$50"),
        Booking(2, "Kitchen Pipe Repair", "Mike Smith", "2024-05-12", "02:00 PM", "Pending", "$40")
    )
    
    val jobRequests = listOf(
        JobRequest(1, "Alice Johnson", "Cleaning", "123 Main St", "Tomorrow, 10 AM", "Pending"),
        JobRequest(2, "Bob Brown", "Plumbing", "456 Oak Ave", "May 15, 3 PM", "Pending")
    )
}
