package com.example.bejibx.navigationtest.navigation

interface NavigationTarget<out T> {

    val screen: Screen

    val params: T
}