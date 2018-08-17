package com.example.bejibx.navigationtest.search

import com.example.bejibx.navigationtest.arch.BasePresenter
import com.example.bejibx.navigationtest.cart.CatalogNavigationTarget
import com.example.bejibx.navigationtest.checkout.CheckoutNavigationTarget
import com.example.bejibx.navigationtest.navigation.Router
import io.reactivex.Completable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<SearchView>() {

    fun openCheckout() {
        router.navigateTo(CheckoutNavigationTarget())
    }

    fun processSearch() {
        Completable.timer(3, TimeUnit.SECONDS)
            .schedule()
            .subscribe { router.navigateTo(CatalogNavigationTarget()) }
    }
}