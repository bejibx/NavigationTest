package com.example.bejibx.navigationtest;

import android.app.Application;
import android.content.Context;

import com.example.bejibx.navigationtest.navigation.ApplicationNavigator;
import com.example.bejibx.navigationtest.navigation.NavigatorHolder;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule {

    @Binds
    abstract NavigatorHolder bindsNavigatorHolder(ApplicationNavigator navigatorHolder);

    @Binds
    abstract Context bindsContext(Application application);
}

