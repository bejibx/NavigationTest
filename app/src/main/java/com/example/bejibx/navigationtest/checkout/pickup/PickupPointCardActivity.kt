package com.example.bejibx.navigationtest.checkout.pickup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseActivity
import kotlinx.android.synthetic.main.activity_pickup_point_card.*
import javax.inject.Inject
import javax.inject.Provider

class PickupPointCardActivity : BaseActivity(), PickupPointCardView {

    @Inject
    lateinit var presenterProvider: Provider<PickupPointCardPresenter>

    @InjectPresenter
    lateinit var presenter: PickupPointCardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pickup_point_card)
        selectButton.setOnClickListener { presenter.selectPickupPoint() }
    }

    @ProvidePresenter
    fun providePresenter(): PickupPointCardPresenter = presenterProvider.get()

    companion object {

        fun createIntent(context: Context) = Intent(context, PickupPointCardActivity::class.java)
    }
}