package com.example.bejibx.navigationtest.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseFragment
import com.example.bejibx.navigationtest.ext.singleThreadLazy
import com.example.bejibx.navigationtest.navigation.FragmentNavigationAdapter
import com.example.bejibx.navigationtest.navigation.Tab
import com.example.bejibx.navigationtest.navigation.TabNavigator

class TabContainerFragment : BaseFragment() {

    val tab by singleThreadLazy { arguments!![KEY_TAB] as Tab }

    val navigator: TabNavigator by singleThreadLazy {
        TabNavigator(tab, childFragmentManager, R.id.tabRootContainer, FragmentNavigationAdapter())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        layoutInflater.inflate(R.layout.fragment_tab_container, container, false)

    companion object {

        private const val KEY_TAB = "Tab"

        fun create(tab: Tab) = TabContainerFragment().apply {
            arguments = Bundle().apply { putSerializable(KEY_TAB, tab) }
        }
    }
}