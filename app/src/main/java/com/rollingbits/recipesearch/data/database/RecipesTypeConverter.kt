package com.rollingbits.recipesearch.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rollingbits.recipesearch.models.FoodRecipe

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe) = gson.toJson(foodRecipe)

    @TypeConverter
    fun stringToFoodRecipe(jsonData: String) {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(jsonData, listType)
    }
}