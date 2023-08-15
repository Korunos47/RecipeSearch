package com.rollingbits.recipesearch.util

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.userPreferencesDataStore by preferencesDataStore(
    name = Constants.PREFERENCES_NAME
)