package com.example.bejibx.navigationtest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NavigationTestApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerApplicationComponent.builder()
                .application(this)
                .build()
    }
}
