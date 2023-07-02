package com.wyekings.logger.logger

/**
 *  @author wye on 6/25/23
 */

internal val String?.stringify: String
    get() = this ?: "NULL"