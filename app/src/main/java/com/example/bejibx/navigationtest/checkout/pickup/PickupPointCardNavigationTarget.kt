package com.example.bejibx.navigationtest.checkout.pickup

import com.example.bejibx.navigationtest.navigation.NavigationTarget
import com.example.bejibx.navigationtest.navigation.Screen

object PickupPointCardNavigationTarget : NavigationTarget<Unit> {

    override val screen = Screen.PICKUP_POINT_CARD

    override val params = Unit

    fun extractResult(result: Any) = result as Int
}