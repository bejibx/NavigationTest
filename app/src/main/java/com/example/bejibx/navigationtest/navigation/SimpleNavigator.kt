package com.example.bejibx.navigationtest.navigation

open class SimpleNavigator : Navigator {

    override fun execute(command: NavigationCommand) =
            when (command) {
                is Forward -> handleForward(command)
                is Back -> handleBack(command)
                is SelectTab -> handleSelectTab(command)
            }

    protected open fun handleForward(command: Forward): Boolean {
        return false
    }

    protected open fun handleBack(command: Back): Boolean {
        return false
    }

    protected open fun handleSelectTab(command: SelectTab): Boolean {
        return false
    }
}