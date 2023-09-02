package com.wyekings.logger.logger

/**
 *  @author wye on 6/25/23
 */
enum class Priority(val level: Int) {

    VERBOSE(level = 2),
    DEBUG(level = 3),
    INFO(level = 4),
    WARN(level = 5),
    ERROR(level = 6),
    ASSERT(level = 7);

    companion object {
        fun getByLevel(level: Int): Priority {
            return values().find { it.level == level } ?: DEBUG
        }
    }
}