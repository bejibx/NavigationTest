package com.example.bejibx.navigationtest.checkout;

import android.support.v4.app.FragmentActivity;

import com.example.bejibx.navigationtest.navigation.ActivityNavigator;
import com.example.bejibx.navigationtest.navigation.NavigationContext;
import com.example.bejibx.navigationtest.navigation.Navigator;
import com.example.bejibx.navigationtest.navigation.Screen;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CheckoutActivityModule {

    @Provides
    static NavigationContext provideNavigationContext() {
        return new NavigationContext(Screen.CHECKOUT, null, null);
    }

    @Binds
    abstract FragmentActivity bindsFragmentActivity(CheckoutActivity activity);

    @Binds
    abstract Navigator bindsNavigator(ActivityNavigator navigator);
}
