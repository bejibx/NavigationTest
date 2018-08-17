package com.example.bejibx.navigationtest.main;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.bejibx.navigationtest.R;
import com.example.bejibx.navigationtest.navigation.Navigator;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @Provides
    static FragmentActivity providesFragmentActivity(MainActivity activity) {
        return activity;
    }

    @Provides
    static FragmentManager provideFragmentManager(MainActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Provides
    static int provideContainerViewId() {
        return R.id.contentContainer;
    }

    @Binds
    abstract Navigator bindsNavigator(MainNavigator navigator);

    @Binds
    abstract Activity bindsActivity(MainActivity activity);

    @ContributesAndroidInjector(modules = TabContainerFragmentModule.class)
    abstract TabContainerFragment contributesTabContainerFragment();
}
