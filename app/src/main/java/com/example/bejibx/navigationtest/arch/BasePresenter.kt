package com.example.bejibx.navigationtest.arch

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : MvpView>(
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
) : MvpPresenter<T>() {

    private val disposables = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    protected fun Completable.schedule(): Completable = observeOn(mainScheduler)
        .doOnSubscribe { disposables.add(it) }

    protected fun <T> Maybe<T>.schedule(): Maybe<T> = observeOn(mainScheduler)
        .doOnSubscribe { disposables.add(it) }
}