package com.wyekings.base.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.fragment.app.Fragment

/**
 *  @author wye on 9/5/23
 */

inline fun <reified T : Activity> Context.start(block: Intent.() -> Unit = {}) {
    internalStart(T::class.java,block)
}

 inline fun Context.start(clazz: Class<out Activity>, block: Intent.() -> Unit = {}) {
     internalStart(clazz,block)
}

@PublishedApi
internal inline fun Context.internalStart(clazz: Class<*>, block: Intent.() -> Unit = {}) {
    val intent = Intent(this, clazz).apply {
        if (this@internalStart !is Activity) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        block()
    }
    startActivity(intent)
}


inline fun <reified T : Activity> Fragment.start(block: Intent.() -> Unit = {}) {
    requireContext().start<T>(block)
}

inline fun Fragment.start(clazz: Class<out Activity>, block: Intent.() -> Unit = {}) {
    requireContext().start(clazz,block)
}
