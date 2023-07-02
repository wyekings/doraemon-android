package com.wyekings.logger

import com.wyekings.logger.logger.Priority
import com.wyekings.logger.logger.toAndroidPriority
import timber.log.Timber

/**
 *  @author wangpt on 6/27/23
 */

inline fun Any.timber(
    priority: Priority = Priority.DEBUG,
    tag: String? = null,
    message: () -> String?,
) {
    val tagOrCaller = tag ?: outerClassSimpleTagName()
    Timber.tag(tagOrCaller).log(priority = priority.toAndroidPriority(), message = message())
}

@PublishedApi
internal fun Any.outerClassSimpleTagName(): String {
    val javaClass = this::class.java
    val fullClassName = javaClass.name
    val outerClassName = fullClassName.substringBefore('$')
    val simplerOuterClassName = outerClassName.substringAfterLast('.')
    return if (simplerOuterClassName.isEmpty()) {
        fullClassName
    } else {
        simplerOuterClassName.removeSuffix("Kt")
    }
}
