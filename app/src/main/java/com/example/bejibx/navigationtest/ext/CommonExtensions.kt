package com.example.bejibx.navigationtest.ext

fun <T> singleThreadLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)