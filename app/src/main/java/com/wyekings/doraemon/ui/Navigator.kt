package com.wyekings.doraemon.ui

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  @author wye on 9/2/23
 */
@Singleton
class Navigator @Inject constructor() {

    fun toMain(context: Context) {
        MainActivity.start(context)
    }
    
}