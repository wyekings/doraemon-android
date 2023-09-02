package com.wyekings.doraemon.ui

import android.content.Context
import com.wyekings.doraemon.ui.splash.SplashActivity
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

}