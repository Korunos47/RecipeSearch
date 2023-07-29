package com.rollingbits.recipesearch.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rollingbits.recipesearch.models.FoodRecipe
import com.rollingbits.recipesearch.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}