package com.example.grupochurrasquinhodomanuel.core.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "session")

class SessionPreferences(private val context: Context) {

    companion object {
        private val LOGGED_IN_EMAIL = stringPreferencesKey("logged_in_email")
    }

    // Recupera o e-mail salvo (ou null se não houver)
    val loggedInEmail: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[LOGGED_IN_EMAIL] }

    // Salva o e-mail no DataStore
    suspend fun saveLoggedInEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[LOGGED_IN_EMAIL] = email
        }
    }

    // Limpa o e-mail (ex: no logout)
    suspend fun clearSession() {
        context.dataStore.edit { preferences ->
            preferences.remove(LOGGED_IN_EMAIL)
        }
    }
}
