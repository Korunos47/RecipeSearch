package com.rollingbits.recipesearch.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.rollingbits.recipesearch.data.database.RecipesEntity
import com.rollingbits.recipesearch.models.FoodRecipe
import com.rollingbits.recipesearch.util.NetworkResult

class RecipesBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("readApiResponseIv", "readDatabaseIv", requireAll = true)
        fun errorImageViewVisibility(
            imageView: ImageView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            } else {
                imageView.visibility = View.INVISIBLE
            }
        }

        @JvmStatic
        @BindingAdapter("readApiResponseTv", "readDatabaseTv", requireAll = true)
        fun errorTextViewVisibility(
            textView: TextView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            } else {
                textView.visibility = View.INVISIBLE
            }
        }
    }
}