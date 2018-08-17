package com.example.bejibx.navigationtest.checkout.pickup

import com.arellomobile.mvp.InjectViewState
import com.example.bejibx.navigationtest.arch.BasePresenter
import com.example.bejibx.navigationtest.navigation.Router
import javax.inject.Inject

@InjectViewState
class PickupPointCardPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<PickupPointCardView>() {

    fun selectPickupPoint() {
        router.backWithResult(123)
    }
}