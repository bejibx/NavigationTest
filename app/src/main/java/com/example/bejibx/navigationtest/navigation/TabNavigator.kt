package com.example.bejibx.navigationtest.navigation

import android.support.v4.app.FragmentManager
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.ScrollableContainer

class TabNavigator(
    val tab: Tab,
    private val fragmentManager: FragmentManager,
    private val containerViewId: Int,
    private val adapter: FragmentNavigationAdapter
) {

    val stackSize
        get() = fragmentManager.fragments?.size ?: 0

    fun add(navigationTarget: NavigationTarget<*>): Boolean {
        val fragment = adapter.createFragment(navigationTarget)
        return if (fragment != null) {
            val transaction = fragmentManager.beginTransaction().add(containerViewId, fragment)
            if (stackSize > 0) {
                transaction.addToBackStack(stackSize.toString())
            }
            transaction.commit()
            true
        } else {
            false
        }
    }

    fun back() = fragmentManager.popBackStackImmediate()

    fun backToRoot() {
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun scrollContentUp() {
        val topFragment = fragmentManager.findFragmentById(R.id.tabRootContainer)
        if (topFragment is ScrollableContainer) {
            topFragment.scrollContentUp()
        }
    }
}