package com.example.bejibx.navigationtest.checkout.map

import com.arellomobile.mvp.InjectViewState
import com.example.bejibx.navigationtest.arch.BasePresenter
import com.example.bejibx.navigationtest.checkout.pickup.PickupPointCardNavigationTarget
import com.example.bejibx.navigationtest.navigation.Router
import javax.inject.Inject

@InjectViewState
class PickupPointsPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<PickupPointsView>() {

    fun openPickupPointCard() {
        router.navigateForResult(PickupPointCardNavigationTarget)
            .schedule()
            .subscribe {
                val result: Int = PickupPointCardNavigationTarget.extractResult(it)
                router.backWithResult(result)
            }
    }

    fun selectPickupPoint() {
        router.backWithResult(42)
    }
}