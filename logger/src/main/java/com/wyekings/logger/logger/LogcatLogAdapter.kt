package com.wyekings.logger.logger

/**
 *  @author wye on 6/25/23
 */
internal open class LogcatLogAdapter
    (private val formatStrategy: FormatStrategy = PrettyFormatStrategy.builder().build()) :
    LogAdapter {
    override fun log(priority: Priority, tag: String?, message: String?) {
        formatStrategy.log(priority, tag, message)
    }
}