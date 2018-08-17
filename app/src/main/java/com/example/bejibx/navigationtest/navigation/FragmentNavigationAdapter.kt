package com.example.bejibx.navigationtest.navigation

import android.support.v4.app.Fragment
import com.example.bejibx.navigationtest.cart.CartFragment
import com.example.bejibx.navigationtest.cart.CatalogFragment
import com.example.bejibx.navigationtest.cart.ProfileFragment
import com.example.bejibx.navigationtest.home.HomeFragment
import com.example.bejibx.navigationtest.search.SearchFragment

class FragmentNavigationAdapter {

    fun createFragment(navigationTarget: NavigationTarget<*>): Fragment? {
        val screen = navigationTarget.screen
        return when (screen) {
            Screen.HOME -> HomeFragment.create()
            Screen.CATALOG -> CatalogFragment.create()
            Screen.CART -> CartFragment.create()
            Screen.PROFILE -> ProfileFragment.create()
            Screen.SEARCH -> SearchFragment.create()
            else -> null
        }
    }
}