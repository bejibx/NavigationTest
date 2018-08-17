package com.example.bejibx.navigationtest.home

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

class HomeNavigationTarget : NavigationTarget<Unit> {

    override val screen = Screen.HOME

    override val params = Unit
}