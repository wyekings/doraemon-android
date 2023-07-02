package com.wyekings.logger.logger

class PrettyFormatStrategy private constructor(builder: Builder) : FormatStrategy {

    private val methodCount: Int = builder.methodCount
    private val methodOffset: Int = builder.methodOffset
    private val showThreadInfo: Boolean = builder.showThreadInfo
    private val logStrategy: LogStrategy = builder.logStrategy

    override fun log(priority: Priority, tag: String?, message: String?) {
        logTopBorder(priority, tag)
        logHeaderContent(priority, tag, methodCount)
        val bytes = message?.toByteArray() ?: ByteArray(0)
        val length = bytes.size
        if (length <= CHUNK_SIZE) {
            if (methodCount > 0) logDivider(priority, tag)
            logContent(priority, tag, message)
            logBottomBorder(priority, tag)
            return
        }

        if (methodCount > 0) {
            logDivider(priority, tag)
        }

        var i = 0
        while (i < length) {
            val count = (length - i).coerceAtMost(CHUNK_SIZE)
            logContent(priority, tag, String(bytes, i, count))
            i += CHUNK_SIZE
        }
        logBottomBorder(priority, tag)
    }

    private fun logTopBorder(logType: Priority, tag: String?) {
        logChunk(logType, tag, TOP_BORDER)
    }

    private fun logHeaderContent(logType: Priority, tag: String?, methodCount: Int) {
        var count = methodCount
        val trace = Thread.currentThread().stackTrace
        if (showThreadInfo) {
            logChunk(
                logType, tag, HORIZONTAL_LINE.toString() + " Thread: " + Thread.currentThread().name
            )
            logDivider(logType, tag)
        }
        var level = ""
        val stackOffset = getStackOffset(trace) + methodOffset

        if (count + stackOffset > trace.size) {
            count = trace.size - stackOffset - 1
        }

        for (i in count downTo 1) {
            val stackIndex = i + stackOffset
            if (stackIndex >= trace.size) {
                continue
            }
            val builder = StringBuilder()
            builder.append(HORIZONTAL_LINE).append(' ').append(level).append(
                getSimpleClassName(trace[stackIndex].className)
            ).append(".").append(trace[stackIndex].methodName).append(" ").append(" (").append(
                trace[stackIndex].fileName
            ).append(":").append(trace[stackIndex].lineNumber).append(")")
            level += "   "
            logChunk(logType, tag, builder.toString())
        }
    }

    private fun logBottomBorder(logType: Priority, tag: String?) {
        logChunk(logType, tag, BOTTOM_BORDER)
    }

    private fun logDivider(logType: Priority, tag: String?) {
        logChunk(logType, tag, MIDDLE_BORDER)
    }

    private fun logContent(logType: Priority, tag: String?, chunk: String?) {
        val lines =
            chunk?.split(System.lineSeparator())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
                ?: emptyArray()
        for (line in lines) {
            logChunk(logType, tag, "$HORIZONTAL_LINE $line")
        }
    }

    private fun logChunk(priority: Priority, tag: String?, chunk: String) {
        logStrategy.log(priority, tag, chunk)
    }

    private fun getSimpleClassName(name: String): String {
        val lastIndex = name.lastIndexOf(".")
        return name.substring(lastIndex + 1)
    }

    private fun getStackOffset(trace: Array<StackTraceElement>): Int {
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != LoggerPrinter::class.java.name && name != Logger::class.java.name) {
                return --i
            }
            i++
        }
        return -1
    }

    class Builder {
        var methodCount = 1
            private set
        var methodOffset = 4
            private set
        var showThreadInfo = true
            private set
        var logStrategy: LogStrategy = LogcatLogStrategy()
            private set

        fun methodCount(methodCount: Int): Builder {
            this.methodCount = methodCount
            return this
        }

        fun methodOffset(methodOffset: Int): Builder {
            this.methodOffset = methodOffset
            return this
        }

        fun showThreadInfo(showThreadInfo: Boolean): Builder {
            this.showThreadInfo = showThreadInfo
            return this
        }

        fun logStrategy(logStrategy: LogStrategy): Builder {
            this.logStrategy = logStrategy
            return this
        }

        fun build(): PrettyFormatStrategy {
            return PrettyFormatStrategy(this)
        }
    }

    companion object {
        private const val CHUNK_SIZE = 4000
        private const val MIN_STACK_OFFSET = 5
        private const val TOP_LEFT_CORNER = '┌'
        private const val BOTTOM_LEFT_CORNER = '└'
        private const val MIDDLE_CORNER = '├'
        private const val HORIZONTAL_LINE = '│'
        private const val DOUBLE_DIVIDER =
            "────────────────────────────────────────────────────────"
        private const val SINGLE_DIVIDER =
            "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
        private const val TOP_BORDER = TOP_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
        private const val BOTTOM_BORDER =
            BOTTOM_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
        private const val MIDDLE_BORDER = MIDDLE_CORNER.toString() + SINGLE_DIVIDER + SINGLE_DIVIDER
        fun builder(): Builder {
            return Builder()
        }
    }
}
