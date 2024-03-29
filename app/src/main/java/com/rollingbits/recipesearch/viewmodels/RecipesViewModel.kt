package com.rollingbits.recipesearch.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rollingbits.recipesearch.data.DataStoreRepository
import com.rollingbits.recipesearch.util.Constants.Companion.API_KEY
import com.rollingbits.recipesearch.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.rollingbits.recipesearch.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.rollingbits.recipesearch.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.rollingbits.recipesearch.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.rollingbits.recipesearch.util.Constants.Companion.QUERY_API_KEY
import com.rollingbits.recipesearch.util.Constants.Companion.QUERY_DIET
import com.rollingbits.recipesearch.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.rollingbits.recipesearch.util.Constants.Companion.QUERY_NUMBER
import com.rollingbits.recipesearch.util.Constants.Companion.QUERY_SEARCH
import com.rollingbits.recipesearch.util.Constants.Companion.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkStatus = false
    var isOnline = false

    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    val readIsOnline = dataStoreRepository.readNetworkStatus.asLiveData()

    fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
    }

    fun saveNetworkStatus(networkStatus: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveNetworkStatus(networkStatus)
        }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries = hashMapOf<String, String>()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            saveNetworkStatus(true)
        } else if (networkStatus) {
            if (isOnline) {
                Toast.makeText(getApplication(), "We're back online", Toast.LENGTH_SHORT).show()
                saveNetworkStatus(false)
            }
        }
    }
}