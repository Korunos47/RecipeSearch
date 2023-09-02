package com.rollingbits.recipesearch.ui.viewpager.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rollingbits.recipesearch.databinding.FragmentIngredientsBinding
import com.rollingbits.recipesearch.models.Result
import com.rollingbits.recipesearch.util.Constants.Companion.RECIPE_RESULT_KEY

class IngredientsFragment : Fragment() {

    private val adapter: IngredientsAdapter by lazy { IngredientsAdapter() }
    private lateinit var binding: FragmentIngredientsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val args = arguments
        val bundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        setupRecyclerView()
        bundle?.extendedIngredients?.let {
            adapter.setData(it)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.ingredientsRV.adapter = adapter
        binding.ingredientsRV.layoutManager = LinearLayoutManager(requireContext())
    }

}