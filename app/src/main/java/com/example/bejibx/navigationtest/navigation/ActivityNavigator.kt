package com.example.bejibx.navigationtest.navigation

import android.support.v4.app.FragmentActivity
import javax.inject.Inject

class ActivityNavigator @Inject constructor(
        private val activity: FragmentActivity,
        private val adapter: ActivitiesNavigationAdapter) : SimpleNavigator() {

    override fun handleForward(command: Forward): Boolean {
        val intent = adapter.createIntent(command.target)
        return if (intent != null) {
            activity.startActivity(intent)
            true
        } else {
            false
        }
    }

    override fun handleBack(command: Back): Boolean {
        if (!activity.supportFragmentManager.popBackStackImmediate()) {
            activity.finish()
        }
        return true
    }
}