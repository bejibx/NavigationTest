package com.example.bejibx.navigationtest.di;

import com.example.bejibx.navigationtest.navigation.ActivityNavigator;
import com.example.bejibx.navigationtest.navigation.Navigator;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ActivityModule {

    @Binds
    abstract Navigator bindsNavigator(ActivityNavigator navigator);
}
