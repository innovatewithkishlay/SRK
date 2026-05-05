package com.fixmate.app.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {
    
    companion object {
        val ROLE_KEY = stringPreferencesKey("user_role")
    }
    
    // Save role (User, Provider, Admin)
    // UNIT V: DataStore Implementation
    suspend fun saveRole(role: String) {
        context.dataStore.edit { preferences ->
            preferences[ROLE_KEY] = role
        }
    }
    
    val getRole: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[ROLE_KEY]
    }
}
