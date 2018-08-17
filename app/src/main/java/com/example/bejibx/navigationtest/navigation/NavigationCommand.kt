package com.example.bejibx.navigationtest.navigation

sealed class NavigationCommand

data class Forward(val target: NavigationTarget<*>, val context: NavigationContext) :
    NavigationCommand()

object Back : NavigationCommand()

data class SelectTab(val tab: Tab) : NavigationCommand()