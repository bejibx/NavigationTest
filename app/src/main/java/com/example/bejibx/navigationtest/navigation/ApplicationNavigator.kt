package com.example.bejibx.navigationtest.navigation

import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import io.reactivex.annotations.CheckReturnValue
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class ApplicationNavigator @Inject constructor() : NavigatorHolder, Navigator {

    private val resultEmitters: MutableMap<ListenerKey, MutableList<MaybeEmitter<Any>>> = HashMap()
    private val navigationCommands: Queue<NavigationCommand> = LinkedList<NavigationCommand>()

    private var activeNavigator: Navigator? = null

    val listenersCount
        get() = resultEmitters.asSequence()
            .map { it.value.size }
            .sum()

    override fun setNavigator(navigator: Navigator) {
        if (activeNavigator != null) {
            throw IllegalStateException(
                "Trying to set new navigator before removing old one! " +
                        "Active navigator is $activeNavigator, new navigator is $navigator."
            )
        }
        activeNavigator = navigator
        drainQueue()
    }

    override fun removeNavigator(navigator: Navigator) {
        if (activeNavigator != null && activeNavigator != navigator) {
            throw IllegalStateException(
                "Trying to remove navigator $navigator, but current " +
                        "navigator is in fact $activeNavigator!"
            )
        }
        this.activeNavigator = null
    }

    override fun execute(command: NavigationCommand): Boolean {
        navigationCommands.add(command)
        drainQueue()
        return true
    }

    private fun drainQueue() {
        var navigator = activeNavigator
        var command = navigationCommands.peek()
        while (navigator != null && command != null) {
            navigator.execute(command)
            navigationCommands.remove()

            navigator = activeNavigator
            command = navigationCommands.peek()
        }
    }

    @CheckReturnValue
    fun registerResultListener(targetScreen: Screen, context: NavigationContext): Maybe<Any> {
        val key = ListenerKey(context.currentScreen, targetScreen)
        val emittersForKey = getEmittersForKey(key)
        return Maybe.create<Any> { emitter: MaybeEmitter<Any> ->
            emitter.setCancellable { removeEmitter(emitter) }
            emittersForKey.add(emitter)
        }
    }

    private fun getEmittersForKey(key: ListenerKey): MutableList<MaybeEmitter<Any>> {
        var emittersForKey = resultEmitters[key]
        if (emittersForKey == null) {
            emittersForKey = LinkedList()
            resultEmitters[key] = emittersForKey
        }
        return emittersForKey
    }

    private fun removeEmitter(emitter: MaybeEmitter<*>) {
        resultEmitters.iterator().apply {
            while (hasNext()) {
                val entry = next()
                val list = entry.value
                list.remove(emitter)
                if (list.isEmpty()) {
                    remove()
                }
            }
        }
    }

    fun sendResult(context: NavigationContext, result: Any) {
        notifyEmitters(context) {
            it.onSuccess(result)
        }
    }

    private fun notifyEmitters(context: NavigationContext, action: (MaybeEmitter<Any>) -> Unit) {
        val currentScreen = context.currentScreen
        val sourceScreen = context.sourceScreen
        val emitters = resultEmitters.asSequence()
            .filter {
                it.key.targetScreen == currentScreen &&
                        (sourceScreen == null || it.key.sourceScreen == sourceScreen)
            }
            .flatMap { it.value.asSequence() }
            .toList()
        for (emitter in emitters) {
            action(emitter)
        }
    }

    fun sendEmptyResult(context: NavigationContext) {
        notifyEmitters(context) {
            it.onComplete()
        }
    }

    private data class ListenerKey(
        val sourceScreen: Screen?,
        val targetScreen: Screen
    )
}