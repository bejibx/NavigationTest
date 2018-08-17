package com.example.bejibx.navigationtest.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import com.example.bejibx.navigationtest.R
import com.example.bejibx.navigationtest.arch.BaseActivity
import com.example.bejibx.navigationtest.cart.SearchNavigationTarget
import com.example.bejibx.navigationtest.navigation.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainNavigator: MainNavigator

    private var isFreshStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isFreshStart = savedInstanceState == null

        firstTabView.setOnClickListener {
            navigator.execute(SelectTab(Tab.ONE))
        }
        secondTabView.setOnClickListener {
            navigator.execute(SelectTab(Tab.TWO))
        }
        thirdTabView.setOnClickListener {
            navigator.execute(SelectTab(Tab.THREE))
        }
        fourthTabView.setOnClickListener {
            navigator.execute(SelectTab(Tab.FOUR))
        }
        mainNavigator.currentTabStream.subscribe { highlightTab(it) }
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuItemNavigate -> {
                    navigator.execute(
                        Forward(
                            SearchNavigationTarget(),
                            NavigationContext(Screen.HOME, null, null)
                        )
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun highlightTab(tab: Tab) {
        val viewToTint = when (tab) {
            Tab.ONE -> firstTabView
            Tab.TWO -> secondTabView
            Tab.THREE -> thirdTabView
            Tab.FOUR -> fourthTabView
        }
        sequenceOf(firstTabView, secondTabView, thirdTabView, fourthTabView)
            .forEach {
                it.imageTintList = if (it === viewToTint)
                    ColorStateList.valueOf(Color.BLUE)
                else
                    ColorStateList.valueOf(Color.WHITE)
            }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (isFreshStart) {
            navigator.execute(SelectTab(Tab.ONE))
            isFreshStart = false
        }
    }
}
