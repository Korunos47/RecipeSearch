package com.rollingbits.recipesearch.ui.viewpager.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.rollingbits.recipesearch.databinding.FragmentInstructionsBinding
import com.rollingbits.recipesearch.models.Result
import com.rollingbits.recipesearch.util.Constants

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInstructionsBinding.inflate(layoutInflater, container, false)

        val args = arguments
        val bundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)

        binding.instructionsWV.webViewClient = object : WebViewClient() {}
        bundle?.sourceUrl?.let { binding.instructionsWV.loadUrl(it) }

        return binding.root
    }

}