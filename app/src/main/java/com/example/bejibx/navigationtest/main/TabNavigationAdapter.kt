package com.example.bejibx.navigationtest.main

import com.example.bejibx.navigationtest.cart.CartNavigationTarget
import com.example.bejibx.navigationtest.cart.CatalogNavigationTarget
import com.example.bejibx.navigationtest.cart.SearchNavigationTarget
import com.example.bejibx.navigationtest.home.HomeNavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen
import com.example.bejibx.navigationtest.navigation.Tab
import javax.inject.Inject

class TabNavigationAdapter @Inject constructor() {

    fun getRootScreenForTab(tab: Tab) = when (tab) {
        Tab.ONE -> Screen.HOME
        Tab.TWO -> Screen.CATALOG
        Tab.THREE -> Screen.CART
        Tab.FOUR -> Screen.SEARCH
    }

    fun getRootNavigationTargetForTab(tab: Tab) = when (tab) {
        Tab.ONE -> HomeNavigationTarget()
        Tab.TWO -> CatalogNavigationTarget()
        Tab.THREE -> CartNavigationTarget()
        Tab.FOUR -> SearchNavigationTarget()
    }

    fun getTabForScreen(screen: Screen) = when (screen) {
        Screen.HOME -> Tab.ONE
        Screen.CATALOG -> Tab.TWO
        Screen.CART -> Tab.THREE
        Screen.SEARCH -> Tab.FOUR
        else -> null
    }
}