package com.example.grupochurrasquinhodomanuel.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore escopado ao Context
private val Context.favoriteDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "favorite_prefs"
)

class FavoritePreferences(private val context: Context) {

    // Chave onde guardamos os IDs (String) dos produtos favoritos
    private val FAVORITE_IDS = stringSetPreferencesKey("favorite_ids")

    /** Flow reativo com os IDs favoritos atuais */
    val favoriteIds: Flow<Set<String>> =
        context.favoriteDataStore.data.map { prefs ->
            prefs[FAVORITE_IDS] ?: emptySet()
        }

    /** Adiciona um ID à lista de favoritos */
    suspend fun add(id: String) {
        context.favoriteDataStore.edit { prefs ->
            val current = prefs[FAVORITE_IDS]?.toMutableSet() ?: mutableSetOf()
            current.add(id)
            prefs[FAVORITE_IDS] = current
        }
    }

    /** Remove um ID da lista de favoritos */
    suspend fun remove(id: String) {
        context.favoriteDataStore.edit { prefs ->
            val current = prefs[FAVORITE_IDS]?.toMutableSet() ?: mutableSetOf()
            current.remove(id)
            prefs[FAVORITE_IDS] = current
        }
    }

    /** Opcional: limpa todos os favoritos */
    suspend fun clear() {
        context.favoriteDataStore.edit { prefs ->
            prefs[FAVORITE_IDS] = emptySet()
        }
    }
}
