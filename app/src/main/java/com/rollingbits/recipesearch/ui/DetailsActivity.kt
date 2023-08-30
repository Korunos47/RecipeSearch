package com.rollingbits.recipesearch.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.rollingbits.recipesearch.R
import com.rollingbits.recipesearch.databinding.ActivityDetailsBinding
import com.rollingbits.recipesearch.ui.viewpager.IngredientsFragment
import com.rollingbits.recipesearch.ui.viewpager.InstructionsFragment
import com.rollingbits.recipesearch.ui.viewpager.OverviewFragment
import com.rollingbits.recipesearch.ui.viewpager.PagerAdapter

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val args by navArgs<DetailsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            setSupportActionBar(toolbar)
            toolbar.setTitleTextColor(ContextCompat.getColor(this@DetailsActivity, R.color.white))
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())


        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val bundle = Bundle()
        bundle.putParcelable("recipeBundle", args.result)

        val adapter = PagerAdapter(
            bundle,
            fragments,
            this
        )

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}