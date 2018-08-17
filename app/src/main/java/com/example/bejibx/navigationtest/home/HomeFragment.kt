package com.example.bejibx.navigationtest.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseFragment
import com.example.bejibx.navigationtest.ext.compoundDrawableLeft
import com.example.bejibx.navigationtest.ext.getDrawableCompat
import com.example.bejibx.navigationtest.rating.RatingDrawable
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ratingDrawable = RatingDrawable.builder()
                .colorHighlight(Color.parseColor("#fd8949"))
                .colorNormal(Color.parseColor("#dcdcdc"))
                .count(5)
                .mask(requireContext().getDrawableCompat(R.drawable.ic_rating_big_cropped)!!)
                .offset(12)
                .build()
        ratingView.compoundDrawableLeft = ratingDrawable
        ratingView.setOnClickListener {}
        ratingDrawable.setHighlightedCount(3)
    }

    companion object {

        fun create() = HomeFragment()
    }
}