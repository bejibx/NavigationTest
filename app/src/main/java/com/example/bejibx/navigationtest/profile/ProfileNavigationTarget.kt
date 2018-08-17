package com.example.bejibx.navigationtest.cart

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

class ProfileNavigationTarget : NavigationTarget<Unit> {

    override val screen = Screen.PROFILE

    override val params = Unit
}