package com.rollingbits.recipesearch.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rollingbits.recipesearch.util.Constants

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries["number"] = "50"
        queries["apiKey"] = Constants.API_KEY
        queries["type"] = "snack"
        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"

        return queries
    }
}