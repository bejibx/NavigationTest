package com.example.bejibx.navigationtest.checkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseActivity
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject
import javax.inject.Provider

class CheckoutActivity : BaseActivity(), CheckoutView {

    @Inject
    lateinit var presenterProvider: Provider<CheckoutPresenter>

    @InjectPresenter
    lateinit var presenter: CheckoutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        successButton.setOnClickListener { presenter.createOrder() }
        pickupPointsButton.setOnClickListener { presenter.showPickupPoints() }
    }

    override fun selectPickupPoint(id: Int) {
        Toast.makeText(this, "Выбрана точка $id", Toast.LENGTH_SHORT).show()
    }

    @ProvidePresenter
    fun providePresenter(): CheckoutPresenter = presenterProvider.get()

    companion object {

        fun createIntent(context: Context) = Intent(context, CheckoutActivity::class.java)
    }
}