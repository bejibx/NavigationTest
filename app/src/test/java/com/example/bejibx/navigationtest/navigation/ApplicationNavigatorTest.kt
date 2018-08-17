package com.example.bejibx.navigationtest.navigation

import io.reactivex.disposables.CompositeDisposable
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class ApplicationNavigatorTest {

    private val navigator = ApplicationNavigator()

    @Test
    fun `Properly registers result listener`() {
        navigator.registerResultListener(
            Screen.PICKUP_POINTS,
            NavigationContext(Screen.CHECKOUT, null, null)
        ).subscribe()

        assertThat(navigator.listenersCount, equalTo(1))
    }

    @Test
    fun `Properly registers multiple result listeners for same screen`() {
        val targetScreen = Screen.PICKUP_POINTS
        val context = NavigationContext(Screen.CHECKOUT, null, null)

        navigator.registerResultListener(targetScreen, context).subscribe()
        navigator.registerResultListener(targetScreen, context).subscribe()

        assertThat(navigator.listenersCount, equalTo(2))
    }

    @Test
    fun `Properly registers multiple result listeners for different target screens`() {

        navigator.registerResultListener(
            Screen.PICKUP_POINTS,
            NavigationContext(Screen.CHECKOUT, null, null)
        ).subscribe()

        navigator.registerResultListener(
            Screen.HOME,
            NavigationContext(Screen.CHECKOUT, null, null)
        ).subscribe()

        assertThat(navigator.listenersCount, equalTo(2))
    }

    @Test
    fun `Properly notifies single registered result listener when result arrives`() {
        val sourceScreenContext = NavigationContext(Screen.CHECKOUT, null, null)
        val targetScreen = Screen.PICKUP_POINTS
        val targetScreenContext = NavigationContext(targetScreen, null, null)
        val result = 42

        val observer = navigator.registerResultListener(targetScreen, sourceScreenContext).test()
        navigator.sendResult(targetScreenContext, result)

        observer.assertResult(result)
    }

    @Test
    fun `Properly notifies multiple registered result listeners when result arrives`() {
        val sourceScreenContext = NavigationContext(Screen.CHECKOUT, null, null)
        val targetScreen = Screen.PICKUP_POINTS
        val targetScreenContext = NavigationContext(targetScreen, null, null)
        val result = 42

        val firstObserver = navigator.registerResultListener(targetScreen, sourceScreenContext)
            .test()
        val secondObserver = navigator.registerResultListener(targetScreen, sourceScreenContext)
            .test()
        navigator.sendResult(targetScreenContext, result)

        firstObserver.assertResult(result)
        secondObserver.assertResult(result)
    }

    @Test
    fun `Unregisters result listener on dispose`() {
        val disposables = CompositeDisposable()
        navigator.registerResultListener(
            Screen.PICKUP_POINTS,
            NavigationContext(Screen.CHECKOUT, null, null)
        )
            .doOnSubscribe { disposables.add(it) }
            .subscribe()
        disposables.dispose()

        assertThat(navigator.listenersCount, equalTo(0))
    }

    @Test
    fun `Do not emit result for listeners registered for different target screen`() {
        val sourceScreenContext = NavigationContext(Screen.CHECKOUT, null, null)
        val targetScreen = Screen.PICKUP_POINTS
        val targetScreenContext = NavigationContext(Screen.PROFILE, null, null)
        val result = 42

        val observer = navigator.registerResultListener(targetScreen, sourceScreenContext).test()
        navigator.sendResult(targetScreenContext, result)

        observer.assertNoValues()
    }

    @Test
    fun `Properly removes registered result listeners after send result`() {
        val sourceScreenContext = NavigationContext(Screen.CHECKOUT, null, null)
        val targetScreen = Screen.PICKUP_POINTS
        val targetScreenContext = NavigationContext(targetScreen, null, null)
        val result = 42

        navigator.registerResultListener(targetScreen, sourceScreenContext).subscribe()
        navigator.registerResultListener(targetScreen, sourceScreenContext).subscribe()
        navigator.sendResult(targetScreenContext, result)

        assertThat(navigator.listenersCount, equalTo(0))
    }

    @Test
    fun `Properly removes registered result listeners after empty result`() {
        val sourceScreenContext = NavigationContext(Screen.CHECKOUT, null, null)
        val targetScreen = Screen.PICKUP_POINTS
        val targetScreenContext = NavigationContext(targetScreen, null, null)

        navigator.registerResultListener(targetScreen, sourceScreenContext).subscribe()
        navigator.registerResultListener(targetScreen, sourceScreenContext).subscribe()
        navigator.sendEmptyResult(targetScreenContext)

        assertThat(navigator.listenersCount, equalTo(0))
    }
}