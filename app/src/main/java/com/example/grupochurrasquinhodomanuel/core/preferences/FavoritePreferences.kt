package com.example.grupochurrasquinhodomanuel.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.favoriteDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "favorite_prefs"
)

class FavoritePreferences(private val context: Context) {

    private val FAVORITE_IDS = stringSetPreferencesKey("favorite_ids")

    val favoriteIds: Flow<Set<String>> =
        context.favoriteDataStore.data.map { prefs ->
            prefs[FAVORITE_IDS] ?: emptySet()
        }

    suspend fun add(id: String) {
        context.favoriteDataStore.edit { prefs ->
            val current = prefs[FAVORITE_IDS]?.toMutableSet() ?: mutableSetOf()
            current.add(id)
            prefs[FAVORITE_IDS] = current
        }
    }

    suspend fun remove(id: String) {
        context.favoriteDataStore.edit { prefs ->
            val current = prefs[FAVORITE_IDS]?.toMutableSet() ?: mutableSetOf()
            current.remove(id)
            prefs[FAVORITE_IDS] = current
        }
    }

    suspend fun clear() {
        context.favoriteDataStore.edit { prefs ->
            prefs[FAVORITE_IDS] = emptySet()
        }
    }
}
