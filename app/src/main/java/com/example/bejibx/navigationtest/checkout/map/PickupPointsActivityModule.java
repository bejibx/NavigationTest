package com.example.bejibx.navigationtest.checkout.map;

import android.support.v4.app.FragmentActivity;

import com.example.bejibx.navigationtest.di.ActivityModule;
import com.example.bejibx.navigationtest.navigation.NavigationContext;
import com.example.bejibx.navigationtest.navigation.Screen;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = ActivityModule.class)
public abstract class PickupPointsActivityModule {

    @Provides
    static NavigationContext provideNavigationContext() {
        return new NavigationContext(Screen.PICKUP_POINTS, null, null);
    }

    @Binds
    abstract FragmentActivity provideActivity(PickupPointsActivity activity);
}
