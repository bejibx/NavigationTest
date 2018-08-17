package com.example.bejibx.navigationtest.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject
import javax.inject.Provider

class SearchFragment : BaseFragment(), SearchView {

    @Inject
    lateinit var presenterProvider: Provider<SearchPresenter>

    @InjectPresenter
    lateinit var presenter: SearchPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openCheckoutButton.setOnClickListener {
            presenter.openCheckout()
        }
        processSearchButton.setOnClickListener {
            presenter.processSearch()
        }
    }

    @ProvidePresenter
    fun providePresenter(): SearchPresenter = presenterProvider.get()

    companion object {

        fun create() = SearchFragment()
    }
}