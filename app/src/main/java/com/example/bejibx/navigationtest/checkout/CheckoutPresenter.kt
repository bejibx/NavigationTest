package com.example.bejibx.navigationtest.checkout

import com.arellomobile.mvp.InjectViewState
import com.example.bejibx.navigationtest.arch.BasePresenter
import com.example.bejibx.navigationtest.checkout.map.PickupPointsNavigationTarget
import com.example.bejibx.navigationtest.navigation.Router
import com.example.bejibx.navigationtest.success.SuccessNavigationTarget
import javax.inject.Inject

@InjectViewState
class CheckoutPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<CheckoutView>() {

    fun createOrder() {
        router.replace(SuccessNavigationTarget())
    }

    fun showPickupPoints() {
        router.navigateForResult(PickupPointsNavigationTarget)
            .schedule()
            .subscribe {
                viewState.selectPickupPoint(PickupPointsNavigationTarget.extractResult(it))
            }
    }
}