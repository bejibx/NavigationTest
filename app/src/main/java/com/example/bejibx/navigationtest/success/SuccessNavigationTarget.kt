package com.example.bejibx.navigationtest.success

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

class SuccessNavigationTarget : NavigationTarget<Unit> {

    override val screen = Screen.SUCCESS

    override val params = Unit
}