package com.example.bejibx.navigationtest.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseFragment

class CatalogFragment : BaseFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    companion object {

        fun create() = CatalogFragment()
    }
}