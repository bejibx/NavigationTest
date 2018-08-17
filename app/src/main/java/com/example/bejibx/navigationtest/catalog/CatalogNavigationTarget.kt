package com.example.bejibx.navigationtest.cart

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

class CatalogNavigationTarget : NavigationTarget<Unit> {

    override val screen = Screen.CATALOG

    override val params = Unit
}