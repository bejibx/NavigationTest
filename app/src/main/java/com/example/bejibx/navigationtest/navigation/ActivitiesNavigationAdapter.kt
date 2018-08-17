package com.example.bejibx.navigationtest.navigation

import android.content.Context
import android.content.Intent
import com.example.bejibx.navigationtest.checkout.CheckoutActivity
import com.example.bejibx.navigationtest.checkout.map.PickupPointsActivity
import com.example.bejibx.navigationtest.checkout.pickup.PickupPointCardActivity
import com.example.bejibx.navigationtest.success.SuccessActivity
import javax.inject.Inject

class ActivitiesNavigationAdapter @Inject constructor(private val context: Context) {

    fun createIntent(navigationTarget: NavigationTarget<*>): Intent? {
        val screen = navigationTarget.screen
        return when (screen) {
            Screen.CHECKOUT -> CheckoutActivity.createIntent(context)
            Screen.SUCCESS -> SuccessActivity.createIntent(context)
            Screen.PICKUP_POINTS -> PickupPointsActivity.createIntent(context)
            Screen.PICKUP_POINT_CARD -> PickupPointCardActivity.createIntent(context)
            else -> null
        }
    }
}