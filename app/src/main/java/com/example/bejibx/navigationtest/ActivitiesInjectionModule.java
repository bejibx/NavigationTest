package com.example.bejibx.navigationtest;

import com.example.bejibx.navigationtest.checkout.CheckoutActivity;
import com.example.bejibx.navigationtest.checkout.CheckoutActivityModule;
import com.example.bejibx.navigationtest.checkout.map.PickupPointsActivity;
import com.example.bejibx.navigationtest.checkout.map.PickupPointsActivityModule;
import com.example.bejibx.navigationtest.checkout.pickup.PickupPointCardActivity;
import com.example.bejibx.navigationtest.checkout.pickup.PickupPointCardActivityModule;
import com.example.bejibx.navigationtest.di.ActivityScope;
import com.example.bejibx.navigationtest.main.MainActivity;
import com.example.bejibx.navigationtest.main.MainActivityModule;
import com.example.bejibx.navigationtest.success.SuccessActivity;
import com.example.bejibx.navigationtest.success.SuccessActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesInjectionModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    @ActivityScope
    abstract MainActivity contributesMainActivity();

    @ContributesAndroidInjector(modules = CheckoutActivityModule.class)
    @ActivityScope
    abstract CheckoutActivity contributesCheckoutActivity();

    @ContributesAndroidInjector(modules = SuccessActivityModule.class)
    @ActivityScope
    abstract SuccessActivity contributesSuccessActivity();

    @ContributesAndroidInjector(modules = PickupPointsActivityModule.class)
    @ActivityScope
    abstract PickupPointsActivity contributesPickupPointsActivity();

    @ContributesAndroidInjector(modules = PickupPointCardActivityModule.class)
    @ActivityScope
    abstract PickupPointCardActivity contributesPickupPointCardActivity();
}
