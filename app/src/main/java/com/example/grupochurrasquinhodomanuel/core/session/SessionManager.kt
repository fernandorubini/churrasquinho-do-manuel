package com.example.grupochurrasquinhodomanuel.core.session

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.grupochurrasquinhodomanuel.core.model.UserType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "session_prefs")

class SessionManager(private val context: Context) {

    companion object {
        private val USER_TYPE = stringPreferencesKey("user_type")
        private val HAS_SEEN_WELCOME = booleanPreferencesKey("has_seen_welcome")
    }

    suspend fun getUserType(): UserType {
        val type = context.dataStore.data.map { prefs ->
            prefs[USER_TYPE] ?: UserType.CUSTOMER.name
        }.first()
        return UserType.valueOf(type)
    }

    suspend fun getStartDestination(): String {
        val hasSeenWelcome = context.dataStore.data.map { prefs ->
            prefs[HAS_SEEN_WELCOME] ?: false
        }.first()
        return if (hasSeenWelcome) "login" else "welcome"
    }
}
