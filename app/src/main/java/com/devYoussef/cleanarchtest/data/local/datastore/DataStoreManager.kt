package com.devYoussef.cleanarchtest.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val DATASTORE_NAME = "app_preferences"

private val Context.dataStore by preferencesDataStore(DATASTORE_NAME)

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun <T> put(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    fun <T> get(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return context.dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

    suspend fun <T> remove(key: Preferences.Key<T>) {
        context.dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}