package com.fixmate.app.data

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*


data class Booking(
    val id: Int,
    val serviceName: String,
    val providerName: String,
    val date: String,
    val time: String,
    val status: String, // Pending, Completed, Rejected
    val price: String
)

data class Service(
    val id: Int,
    val name: String,
    val icon: ImageVector,
    val category: String,
    val rating: Float,
    val price: String,
    val description: String,
    val imageUrl: String
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
        Service(1, "Deep House Cleaning", Icons.Default.CleaningServices, "Cleaning", 4.8f, "$50/hr", "Professional deep cleaning for your entire home.", "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?auto=format&fit=crop&q=80&w=500"),
        Service(2, "Kitchen Pipe Repair", Icons.Default.Plumbing, "Plumbing", 4.5f, "$40/hr", "Fixing leaks and unclogging kitchen pipes.", "https://images.unsplash.com/photo-1581244277943-fe4a9c777189?auto=format&fit=crop&q=80&w=500"),
        Service(3, "Full Home Painting", Icons.Default.FormatPaint, "Painting", 4.9f, "$200/room", "High-quality painting with premium finishes.", "https://images.unsplash.com/photo-1562259949-e8e7689d7828?auto=format&fit=crop&q=80&w=500"),
        Service(4, "Electrical Wiring", Icons.Default.ElectricalServices, "Electrician", 4.7f, "$60/hr", "Safe and reliable electrical wiring services.", "https://images.unsplash.com/photo-1621905251189-08b45d6a269e?auto=format&fit=crop&q=80&w=500"),
        Service(5, "Furniture Assembly", Icons.Default.Carpenter, "Carpentry", 4.6f, "$35/hr", "Expert assembly for all types of furniture.", "https://images.unsplash.com/photo-1530124560676-583c38361093?auto=format&fit=crop&q=80&w=500")
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
