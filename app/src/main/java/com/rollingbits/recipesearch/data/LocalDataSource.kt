package com.rollingbits.recipesearch.data

import com.rollingbits.recipesearch.data.database.RecipesDao
import com.rollingbits.recipesearch.data.database.RecipesEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readDatabase() = recipesDao.readRecipes()

    suspend fun insertRecipes(recipesEntity: RecipesEntity) =
        recipesDao.insertRecipes(recipesEntity)
}