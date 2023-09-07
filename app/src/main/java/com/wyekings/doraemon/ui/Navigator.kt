package com.wyekings.doraemon.ui

import android.content.Context
import com.wyekings.composable.ui.ComposableActivity
import com.wyekings.doraemon.ui.main.MainActivity
import com.wyekings.doraemon.ui.splash.SplashActivity
import com.wyekings.sunflower.SunflowerActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  @author wye on 9/2/23
 */
@Singleton
class Navigator @Inject constructor() {

    fun toSplash(context: Context) {
        SplashActivity.start(context)
    }

    fun toMain(context: Context) {
        MainActivity.start(context)
    }

    fun toComposable(context: Context) {
        ComposableActivity.start(context)
    }

    fun toSunflower(context: Context) {
        SunflowerActivity.start(context)
    }
}