package com.wyekings.logger

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.startup.Initializer
import com.wyekings.logger.timber.TimberDebugTree
import com.wyekings.logger.timber.TimberReleaseTree
import timber.log.Timber

/**
 *  @author wye on 6/19/23
 */
class LoggerInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val isDebug = 0 != context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        Timber.plant(if (isDebug) TimberDebugTree() else TimberReleaseTree())
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}