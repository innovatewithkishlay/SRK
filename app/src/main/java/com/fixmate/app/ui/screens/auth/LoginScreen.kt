package com.fixmate.app.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fixmate.app.ui.components.CustomButton
import com.fixmate.app.ui.components.CustomTextField

@Composable
fun LoginScreen(role: String, onLoginSuccess: () -> Unit, onSignupClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Back",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Login as $role",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        CustomTextField(
            value = email,
            onValueChange = { 
                email = it 
                errorMessage = null
            },
            label = "Email Address",
            leadingIcon = Icons.Default.Email
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = password,
            onValueChange = { 
                password = it 
                errorMessage = null
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation()
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp).align(Alignment.Start)
            )
        }
        
        TextButton(
            onClick = { /* Forgot password */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Forgot Password?")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        CustomButton(
            text = "Login",
            onClick = {
                if (role == "Admin") {
                    if (email == "fixmate@fixmate.in" && password == "fixmate90") {
                        onLoginSuccess()
                    } else {
                        errorMessage = "Invalid Admin Credentials"
                    }
                } else {
                    // For User and Provider, any non-empty login works for demo
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        onLoginSuccess()
                    } else {
                        errorMessage = "Please enter email and password"
                    }
                }
            }
        )
        
        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?")
            TextButton(onClick = onSignupClick) {
                Text("Sign Up", fontWeight = FontWeight.Bold)
            }
        }
    }
}
