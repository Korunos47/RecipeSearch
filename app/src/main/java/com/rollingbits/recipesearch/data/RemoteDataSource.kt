package com.rollingbits.recipesearch.data

import com.rollingbits.recipesearch.data.network.FoodRecipesApi
import com.rollingbits.recipesearch.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> =
        foodRecipesApi.getRecipes(queries)

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }
}