package com.example.bejibx.navigationtest.checkout.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseActivity
import kotlinx.android.synthetic.main.activity_pickup_points.*
import javax.inject.Inject
import javax.inject.Provider

class PickupPointsActivity : BaseActivity(), PickupPointsView {

    @Inject
    lateinit var presenterProvider: Provider<PickupPointsPresenter>

    @InjectPresenter
    lateinit var presenter: PickupPointsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pickup_points)
        selectButton.setOnClickListener { presenter.selectPickupPoint() }
        moreInfoButton.setOnClickListener { presenter.openPickupPointCard() }
    }

    @ProvidePresenter
    fun providePresenter(): PickupPointsPresenter = presenterProvider.get()

    companion object {

        fun createIntent(context: Context) = Intent(context, PickupPointsActivity::class.java)
    }
}