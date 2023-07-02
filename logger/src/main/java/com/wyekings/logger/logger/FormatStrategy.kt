package com.wyekings.logger.logger

/**
 *  @author wye on 6/25/23
 */
interface FormatStrategy {
    fun log(priority: Priority, tag: String?, message: String?)
}