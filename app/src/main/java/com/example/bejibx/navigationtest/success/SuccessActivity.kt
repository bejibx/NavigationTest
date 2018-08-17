package com.example.bejibx.navigationtest.success

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseActivity
import kotlinx.android.synthetic.main.activity_success.*
import javax.inject.Inject
import javax.inject.Provider

class SuccessActivity : BaseActivity(), SuccessView {

    @Inject
    lateinit var presenterProvider: Provider<SuccessPresenter>

    @InjectPresenter
    lateinit var presenter: SuccessPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        continueButton.setOnClickListener { presenter.continueShopping() }
    }

    @ProvidePresenter
    fun providePresenter() = presenterProvider.get()!!

    companion object {

        fun createIntent(context: Context) = Intent(context, SuccessActivity::class.java)
    }
}