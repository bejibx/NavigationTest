package com.example.bejibx.navigationtest.cart

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

class SearchNavigationTarget : NavigationTarget<Unit> {

    override val screen = Screen.SEARCH

    override val params = Unit
}