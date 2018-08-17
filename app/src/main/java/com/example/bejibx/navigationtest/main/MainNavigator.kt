package com.example.bejibx.navigationtest.main

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.bejibx.navigationtest.di.ActivityScope
import com.example.bejibx.navigationtest.navigation.*
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

private val INITIAL_TAB = Tab.ONE

@ActivityScope
class MainNavigator @Inject constructor(
    private val fragmentManager: FragmentManager,
    private val containerViewId: Int,
    private val tabNavigationAdapter: TabNavigationAdapter,
    private val activity: Activity,
    private val tabsBackStack: TabsBackStack,
    private val activityNavigator: ActivityNavigator
) : SimpleNavigator() {

    private val currentTabNavigator
        get() = currentFragment.navigator

    private val currentFragment
        get() = fragmentManager.fragments
            .filterIsInstance<TabContainerFragment>()
            .firstOrNull { it.isVisible }
            ?: getTabContainerFragment(INITIAL_TAB)

    private val currentTab
        get() = currentFragment.tab

    private val currentTabSubject = BehaviorSubject.createDefault<Tab>(INITIAL_TAB)

    val currentTabStream: Observable<Tab> = currentTabSubject.hide()

    private fun selectTab(tab: Tab): Boolean {
        val fragment = getTabContainerFragment(tab)
        if (fragment.isVisible) {
            val navigator = fragment.navigator
            if (navigator.stackSize > 1) {
                navigator.backToRoot()
            } else {
                navigator.scrollContentUp()
            }
        } else {
            showFragment(fragment)
            currentTabSubject.onNext(tab)
            return true
        }
        return false
    }

    private fun getTabContainerFragment(tab: Tab): TabContainerFragment {
        val tabFragment = fragmentManager.findFragmentByTag(createFragmentTag(tab))
        if (tabFragment is TabContainerFragment) {
            return tabFragment
        }
        if (tabFragment != null) {
            throw IllegalStateException(
                "Only TabContainerFragment can be added as tab root, but" +
                        " somehow tab root is $tabFragment!"
            )
        }
        return initializeTab(tab)
    }

    private fun initializeTab(tab: Tab): TabContainerFragment {
        val tabRootFragment = TabContainerFragment.create(tab)
        fragmentManager.beginTransaction()
            .add(containerViewId, tabRootFragment, createFragmentTag(tab))
            .hide(tabRootFragment)
            .commitNow()
        tabRootFragment.navigator.add(tabNavigationAdapter.getRootNavigationTargetForTab(tab))
        return tabRootFragment
    }

    private fun showFragment(tabFragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        fragmentManager.fragments
            .forEach {
                if (it == tabFragment) {
                    transaction.show(it)
                } else {
                    transaction.hide(it)
                }
            }
        transaction.commitNow()
    }

    private fun createFragmentTag(tab: Tab) = tab.name

    override fun handleForward(command: Forward): Boolean {
        var targetTab = tabNavigationAdapter.getTabForScreen(command.target.screen)
        if (targetTab != null) {
            selectTab(targetTab)
        } else {
            targetTab = command.context.tab ?: currentTab
        }
        val tabNavigator = getTabNavigator(targetTab)
        if (tabNavigationAdapter.getRootScreenForTab(targetTab) == command.target.screen) {
            currentTabNavigator.backToRoot()
            return true
        }
        return if (tabNavigator.add(command.target)) {
            true
        } else {
            activityNavigator.execute(command)
        }
    }

    private fun getTabNavigator(tab: Tab) = getTabContainerFragment(tab).navigator

    override fun handleBack(command: Back): Boolean {
        if (!currentTabNavigator.back()) {
            val record = tabsBackStack.poll()
            if (record == null) {
                activity.finish()
            } else {
                selectTab(record.fromTab)
            }
        }
        return true
    }

    override fun handleSelectTab(command: SelectTab): Boolean {
        val currentTab = this.currentTab
        val targetTab = command.tab
        if (selectTab(targetTab))
            tabsBackStack.put(currentTab, targetTab)
        return true
    }
}