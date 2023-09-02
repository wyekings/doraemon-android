package com.wyekings.context

import android.content.Context
import androidx.startup.Initializer

/**
 *  @author wye on 6/19/23
 */
class ContextProviderInitializer:Initializer<Unit> {
    override fun create(context: Context) {
        ContextProvider.attach(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}