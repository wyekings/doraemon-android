package com.wyekings.logger.logger

/**
 *  @author wye on 6/25/23
 */
interface Printer {

    fun addLogAdapter(adapter: LogAdapter)

    fun clearLogAdapters()

    fun log(priority: Priority, tag: String?, message: String)

}