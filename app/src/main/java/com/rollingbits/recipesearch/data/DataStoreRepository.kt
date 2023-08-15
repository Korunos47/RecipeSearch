package com.rollingbits.recipesearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.rollingbits.recipesearch.data.DataStoreRepository.PreferenceKeys.selectedDietId
import com.rollingbits.recipesearch.data.DataStoreRepository.PreferenceKeys.selectedDietType
import com.rollingbits.recipesearch.data.DataStoreRepository.PreferenceKeys.selectedMealType
import com.rollingbits.recipesearch.data.DataStoreRepository.PreferenceKeys.selectedMealTypeId
import com.rollingbits.recipesearch.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.rollingbits.recipesearch.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.rollingbits.recipesearch.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.rollingbits.recipesearch.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.rollingbits.recipesearch.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.rollingbits.recipesearch.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.rollingbits.recipesearch.util.userPreferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {
    private val dataStore: DataStore<Preferences> = context.userPreferencesDataStore

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
    }

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStore.edit { preferences ->
            preferences[selectedMealType] = mealType
            preferences[selectedMealTypeId] = mealTypeId
            preferences[selectedDietType] = dietType
            preferences[selectedDietId] = dietTypeId
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preferences ->
            val selectedMealType = preferences[selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[selectedMealTypeId] ?: 0
            val selectedMealDiet = preferences[selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedMealDietId = preferences[selectedDietId] ?: 0

            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedMealDiet,
                selectedMealDietId
            )
        }
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)