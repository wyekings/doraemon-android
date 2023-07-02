package com.wyekings.logger.timber

import com.wyekings.logger.logger.LogcatLogAdapter
import com.wyekings.logger.logger.Logger
import com.wyekings.logger.logger.PrettyFormatStrategy
import com.wyekings.logger.logger.Priority
import timber.log.Timber

internal class TimberDebugTree : Timber.DebugTree() {

    companion object {
        private const val METHOD_COUNT = 1
        private const val METHOD_OFFSET = 4
    }

    init {
        Logger.addLogAdapter(LogcatLogAdapter(formatStrategy = createPrettyFormatStrategy()))
    }

    private fun createPrettyFormatStrategy(): PrettyFormatStrategy =
        PrettyFormatStrategy.builder().methodCount(METHOD_COUNT).methodOffset(METHOD_OFFSET).build()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Logger.log(Priority.getByLevel(priority), tag, message)
    }
}