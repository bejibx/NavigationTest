package com.example.bejibx.navigationtest.checkout

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

class CheckoutNavigationTarget : NavigationTarget<Unit> {

    override val screen = Screen.CHECKOUT

    override val params = Unit
}