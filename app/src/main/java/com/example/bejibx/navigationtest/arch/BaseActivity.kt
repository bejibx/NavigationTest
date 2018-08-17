package com.example.bejibx.navigationtest.arch

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.CallSuper
import com.arellomobile.mvp.MvpDelegate
import com.example.bejibx.navigationtest.ext.singleThreadLazy
import com.example.bejibx.navigationtest.navigation.Back
import com.example.bejibx.navigationtest.navigation.Navigator
import com.example.bejibx.navigationtest.navigation.NavigatorHolder
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

@SuppressLint("Registered")
open class BaseActivity : DaggerAppCompatActivity() {

    private val mvpDelegate by singleThreadLazy { MvpDelegate<BaseActivity>(this) }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @CallSuper
    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    @CallSuper
    override fun onPause() {
        navigatorHolder.removeNavigator(navigator)
        super.onPause()
    }

    @CallSuper
    override fun finish() {
        super.finish()
        navigatorHolder.removeNavigator(navigator)
    }

    @CallSuper
    override fun onBackPressed() {
        navigator.execute(Back)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpDelegate.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        mvpDelegate.onAttach()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        mvpDelegate.onAttach()
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDelegate.onSaveInstanceState(outState)
        mvpDelegate.onDetach()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        mvpDelegate.onDetach()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        mvpDelegate.onDestroyView()
        if (isFinishing) {
            mvpDelegate.onDestroy()
        }
    }
}