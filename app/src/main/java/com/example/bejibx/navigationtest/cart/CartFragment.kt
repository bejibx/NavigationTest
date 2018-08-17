package com.example.bejibx.navigationtest.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseFragment

class CartFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cart, container, false)

    companion object {

        fun create() = CartFragment()
    }
}