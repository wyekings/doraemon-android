package com.wyekings.context

/**
 *  @author wye
 */
import android.annotation.SuppressLint
import android.content.Context


@SuppressLint("StaticFieldLeak")
object ContextProvider {
    lateinit var context: Context
        private set

    fun attach(context: Context) {
        if (this::context.isInitialized) return
        ContextProvider.context = context.applicationContext
    }
}
