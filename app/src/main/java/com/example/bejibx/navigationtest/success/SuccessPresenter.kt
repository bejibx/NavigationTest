package com.example.bejibx.navigationtest.success

import com.example.bejibx.navigationtest.arch.BasePresenter
import com.example.bejibx.navigationtest.home.HomeNavigationTarget
import com.example.bejibx.navigationtest.navigation.Router
import javax.inject.Inject

class SuccessPresenter @Inject constructor(
        private val router: Router
) : BasePresenter<SuccessView>() {

    fun continueShopping() {
        router.replace(HomeNavigationTarget())
    }
}