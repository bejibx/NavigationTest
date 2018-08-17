package com.example.bejibx.navigationtest.main

import com.example.bejibx.navigationtest.navigation.Tab
import java.util.*
import javax.inject.Inject

class TabsBackStack @Inject constructor() {

    private val backStack = LinkedList<Record>()

    fun put(currentTab: Tab, targetTab: Tab) {
        if (currentTab == targetTab) {
            return
        }
        val record = Record(currentTab, targetTab)
        val index = backStack.indexOf(record)
        if (index >= 0) {
            backStack.removeAt(index)
        }
        backStack.add(record)
    }

    fun poll(): Record? {
        return backStack.pollLast()
    }

    data class Record(val fromTab: Tab, val toTab: Tab)
}

