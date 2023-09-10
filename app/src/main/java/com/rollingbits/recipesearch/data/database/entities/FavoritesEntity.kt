package com.rollingbits.recipesearch.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rollingbits.recipesearch.models.Result
import com.rollingbits.recipesearch.util.Constants.Companion.FAVORITES_RECIPES_TABLE

@Entity(tableName = FAVORITES_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)