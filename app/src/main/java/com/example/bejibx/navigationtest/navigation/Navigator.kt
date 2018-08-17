package com.example.bejibx.navigationtest.navigation

interface Navigator {

    fun execute(command: NavigationCommand): Boolean
}