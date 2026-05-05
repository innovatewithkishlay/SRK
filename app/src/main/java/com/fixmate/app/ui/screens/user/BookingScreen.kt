package com.fixmate.app.ui.screens.user

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fixmate.app.data.DummyData
import com.fixmate.app.ui.components.CustomButton
import com.fixmate.app.ui.components.CustomCard
import com.fixmate.app.ui.components.CustomTextField
import java.util.*

/**
 * UNIT III: Date Picker and Time Picker Dialogs
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(serviceId: Int, onBookingConfirmed: () -> Unit) {
    val context = LocalContext.current
    val service = DummyData.services.find { it.id == serviceId } ?: DummyData.services[0]
    
    var selectedDate by remember { mutableStateOf("Select Date") }
    var selectedTime by remember { mutableStateOf("Select Time") }
    var address by remember { mutableStateOf("") }
    
    val calendar = Calendar.getInstance()
    
    // UNIT III: Date Picker Dialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    
    // UNIT III: Time Picker Dialog
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Book ${service.name}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        CustomCard {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Service Details", fontWeight = FontWeight.Bold)
                Text(text = "Price: ${service.price}", color = MaterialTheme.colorScheme.primary)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // UNIT I: Dropdown (Spinner equivalent)
        var expanded by remember { mutableStateOf(false) }
        val options = listOf("Standard", "Premium", "Express")
        var selectedOption by remember { mutableStateOf(options[0]) }
        
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = { Text("Service Type") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOption = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "Schedule", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        
        OutlinedButton(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(Icons.Default.CalendarMonth, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = selectedDate)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { timePickerDialog.show() },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Icon(Icons.Default.Schedule, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = selectedTime)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        CustomTextField(
            value = address,
            onValueChange = { address = it },
            label = "Service Address"
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        CustomButton(
            text = "Confirm Booking",
            onClick = {
                if (selectedDate != "Select Date" && selectedTime != "Select Time" && address.isNotEmpty()) {
                    onBookingConfirmed()
                }
            }
        )
    }
}
