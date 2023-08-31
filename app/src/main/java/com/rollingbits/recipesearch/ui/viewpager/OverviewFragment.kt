package com.rollingbits.recipesearch.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.rollingbits.recipesearch.R
import com.rollingbits.recipesearch.bindingadapters.RecipesRowBinding
import com.rollingbits.recipesearch.databinding.FragmentOverviewBinding
import com.rollingbits.recipesearch.models.Result


class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)

        val args = arguments
        val bundle: Result? = args?.getParcelable("recipeBundle")

        with(binding) {
            recipeIV.load(bundle?.image)
            titleTV.text = bundle?.title
            likesTV.text = bundle?.aggregateLikes.toString()
            timeTV.text = bundle?.readyInMinutes.toString()
            RecipesRowBinding.parseHtml(summaryTV, bundle?.summary)

            if (bundle?.vegetarian == true) {
                vegetarianIV.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                vegetarianTV.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
            if (bundle?.vegan == true) {
                veganIV.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                veganTV.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
            if (bundle?.glutenFree == true) {
                glutenFreeIV.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                glutenFreeTV.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
            if (bundle?.dairyFree == true) {
                dairyFreeIV.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                dairyFreeTV.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
            if (bundle?.veryHealthy == true) {
                healthyIV.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                healthyTV.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
            if (bundle?.cheap == true) {
                cheapIV.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
                cheapTV.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
        }

        return binding.root
    }
}