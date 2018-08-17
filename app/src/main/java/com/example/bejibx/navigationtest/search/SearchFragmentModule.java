package com.example.bejibx.navigationtest.search;

import com.example.bejibx.navigationtest.navigation.NavigationContext;
import com.example.bejibx.navigationtest.navigation.Screen;
import com.example.bejibx.navigationtest.navigation.Tab;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SearchFragmentModule {

    @Provides
    static NavigationContext provideNavigationContext(Tab tab) {
        return new NavigationContext(Screen.SEARCH, null, tab);
    }
}
