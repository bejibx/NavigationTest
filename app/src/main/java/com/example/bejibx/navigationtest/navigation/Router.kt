package com.example.bejibx.navigationtest.navigation

import io.reactivex.Maybe
import javax.inject.Inject

class Router @Inject constructor(
    private val applicationNavigator: ApplicationNavigator,
    private val context: NavigationContext
) {
    fun navigateTo(target: NavigationTarget<*>) {
        applicationNavigator.execute(Forward(target, context))
    }

    fun replace(target: NavigationTarget<*>) {
        applicationNavigator.execute(Back)
        applicationNavigator.execute(Forward(target, context))
    }

    fun back() {
        applicationNavigator.execute(Back)
        applicationNavigator.sendEmptyResult(context)
    }

    fun navigateForResult(target: NavigationTarget<*>): Maybe<*> {
        applicationNavigator.execute(Forward(target, context))
        return applicationNavigator.registerResultListener(target.screen, context)
    }

    fun backWithResult(result: Any) {
        applicationNavigator.execute(Back)
        applicationNavigator.sendResult(context, result)
    }
}