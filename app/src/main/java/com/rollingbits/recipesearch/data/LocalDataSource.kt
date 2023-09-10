package com.rollingbits.recipesearch.data

import com.rollingbits.recipesearch.data.database.RecipesDao
import com.rollingbits.recipesearch.data.database.entities.FavoritesEntity
import com.rollingbits.recipesearch.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readRecipes() = recipesDao.readRecipes()

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> =
        recipesDao.readFavoriteRecipes()


    fun insertRecipes(recipesEntity: RecipesEntity) =
        recipesDao.insertRecipes(recipesEntity)

    fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) =
        recipesDao.insertFavoriteRecipe(favoritesEntity)


    fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) =
        recipesDao.deleteFavoriteRecipe(favoritesEntity)

    fun deleteAllFavoriteRecipes() = recipesDao.deleteAllFavoriteRecipes()
}