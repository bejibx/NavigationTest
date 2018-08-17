package com.example.bejibx.navigationtest.cart

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

class CartNavigationTarget : NavigationTarget<Unit> {

    override val screen = Screen.CART

    override val params = Unit
}