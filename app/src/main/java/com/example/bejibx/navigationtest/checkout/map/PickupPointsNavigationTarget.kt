package com.example.bejibx.navigationtest.checkout.map

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

object PickupPointsNavigationTarget : NavigationTarget<Unit> {
    override val screen = Screen.PICKUP_POINTS
    override val params = Unit

    fun extractResult(result: Any) = result as Int
}