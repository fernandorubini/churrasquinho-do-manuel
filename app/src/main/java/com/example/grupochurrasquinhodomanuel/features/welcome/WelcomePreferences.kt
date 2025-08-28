package com.example.grupochurrasquinhodomanuel.features.welcome

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREF_NAME = "welcome_prefs"
private val Context.dataStore by preferencesDataStore(name = PREF_NAME)

class WelcomePreferences(private val context: Context) {

    companion object {
        val IS_FIRST_LAUNCH = booleanPreferencesKey("welcome_first_launch")
    }

    val isFirstLaunch: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[IS_FIRST_LAUNCH] ?: true }

    suspend fun setNotFirstLaunch() {
        context.dataStore.edit { prefs ->
            prefs[IS_FIRST_LAUNCH] = false
        }
    }

    suspend fun resetFirstLaunch() {
        context.dataStore.edit { prefs ->
            prefs.remove(IS_FIRST_LAUNCH)
        }
    }
}
