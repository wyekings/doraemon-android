package com.wyekings.logger.logger

/**
 *  @author wye on 6/25/23
 */
interface LogAdapter {

    fun log(priority: Priority, tag: String?, message: String?)
}