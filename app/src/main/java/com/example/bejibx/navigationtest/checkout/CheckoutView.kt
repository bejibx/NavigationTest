package com.example.bejibx.navigationtest.checkout

import com.arellomobile.mvp.MvpView

interface CheckoutView : MvpView {

    fun selectPickupPoint(id: Int)
}