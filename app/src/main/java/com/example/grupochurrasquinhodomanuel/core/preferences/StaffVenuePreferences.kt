package com.example.grupochurrasquinhodomanuel.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.PreferenceDataStoreFactory

data class StaffVenue(val brandId: Long, val unitId: Long)

class StaffVenuePreferences(context: Context) {

    private val dataStore: DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("staff_venue_prefs") }
        )

    private val KEY_BRAND = longPreferencesKey("staff_brand_id")
    private val KEY_UNIT  = longPreferencesKey("staff_unit_id")

    val venueFlow: Flow<StaffVenue?> =
        dataStore.data.map { p ->
            val b = p[KEY_BRAND]
            val u = p[KEY_UNIT]
            if (b != null && u != null) StaffVenue(b, u) else null
        }

    suspend fun set(brandId: Long, unitId: Long) {
        dataStore.edit { p ->
            p[KEY_BRAND] = brandId
            p[KEY_UNIT]  = unitId
        }
    }

    suspend fun clear() {
        dataStore.edit { p ->
            p.remove(KEY_BRAND)
            p.remove(KEY_UNIT)
        }
    }
}
