package com.wyekings.logger.logger

import android.util.Log

/**
 *  @author wye on 6/25/23
 */
class LogcatLogStrategy : LogStrategy {
    override fun log(priority: Priority, tag: String?, message: String?) {
        Log.println(priority.toAndroidPriority(), tag ?: DEFAULT_TAG, message.stringify)
    }

    companion object {
        const val DEFAULT_TAG = "NO_TAG"
    }
}

@PublishedApi
internal fun Priority.toAndroidPriority(): Int {
    return when (this) {
        Priority.VERBOSE -> Log.VERBOSE
        Priority.DEBUG -> Log.DEBUG
        Priority.INFO -> Log.INFO
        Priority.WARN -> Log.WARN
        Priority.ERROR -> Log.ERROR
        Priority.ASSERT -> Log.ASSERT
    }
}
