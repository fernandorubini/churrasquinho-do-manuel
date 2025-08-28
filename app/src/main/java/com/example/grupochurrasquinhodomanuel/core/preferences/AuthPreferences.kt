package com.example.grupochurrasquinhodomanuel.core.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.grupochurrasquinhodomanuel.core.UserType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AuthPreferences(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

        private val EMAIL_KEY: Preferences.Key<String> = stringPreferencesKey("email")
        private val USER_TYPE_KEY: Preferences.Key<String> = stringPreferencesKey("user_type")
    }

    suspend fun saveUserEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL_KEY] = email
        }
    }

    suspend fun getUserEmail(): String? {
        return context.dataStore.data
            .map { prefs -> prefs[EMAIL_KEY] }
            .first()
    }

    suspend fun saveUserType(userType: UserType) {
        context.dataStore.edit { prefs ->
            prefs[USER_TYPE_KEY] = userType.name
        }
    }

    suspend fun getUserType(): UserType? {
        val storedValue: String? = context.dataStore.data
            .map { prefs -> prefs[USER_TYPE_KEY] }
            .first()

        return storedValue?.let { name ->
            try {
                UserType.valueOf(name)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
