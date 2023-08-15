package com.rollingbits.recipesearch.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.rollingbits.recipesearch.databinding.RecipesBottomSheetBinding
import com.rollingbits.recipesearch.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.rollingbits.recipesearch.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.rollingbits.recipesearch.viewmodels.RecipesViewModel
import timber.log.Timber.Forest.d
import java.util.Locale

class RecipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var recipesViewModel: RecipesViewModel
    private var _binding: RecipesBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = RecipesBottomSheetBinding.inflate(inflater, container, false)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType

            updateChip(value.selectedMealTypeId, binding.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
        }

        binding.mealTypeChipGroup.setOnCheckedStateChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId.first())
            val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = selectedChipId.first()
        }

        binding.dietTypeChipGroup.setOnCheckedStateChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId.first())
            val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = selectedChipId.first()
        }

        binding.applyButton.setOnClickListener {
            recipesViewModel.saveMealAndDietType(
                mealTypeChip,
                mealTypeChipId,
                dietTypeChip,
                dietTypeChipId
            )
            val action =
                RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                d(e.message.toString())
            }
        }
    }

}