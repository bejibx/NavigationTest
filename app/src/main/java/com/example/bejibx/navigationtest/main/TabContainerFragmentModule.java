package com.example.bejibx.navigationtest.main;

import com.example.bejibx.navigationtest.cart.CartFragment;
import com.example.bejibx.navigationtest.cart.CatalogFragment;
import com.example.bejibx.navigationtest.cart.ProfileFragment;
import com.example.bejibx.navigationtest.home.HomeFragment;
import com.example.bejibx.navigationtest.navigation.Tab;
import com.example.bejibx.navigationtest.search.SearchFragment;
import com.example.bejibx.navigationtest.search.SearchFragmentModule;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TabContainerFragmentModule {

    @Provides
    static Tab provideTab(TabContainerFragment fragment) {
        return fragment.getTab();
    }

    @ContributesAndroidInjector
    abstract HomeFragment contributesHomeFragment();

    @ContributesAndroidInjector
    abstract CatalogFragment contributesCatalogFragment();

    @ContributesAndroidInjector
    abstract CartFragment contributesCartFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment contributesProfileFragment();

    @ContributesAndroidInjector(modules = SearchFragmentModule.class)
    abstract SearchFragment contributesSearchFragment();
}
