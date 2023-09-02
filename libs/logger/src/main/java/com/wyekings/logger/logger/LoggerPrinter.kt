package com.wyekings.logger.logger

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 *  @author wye on 6/25/23
 */
class LoggerPrinter(
    private val adapters: MutableList<LogAdapter> = mutableListOf(),
) : Printer {
    override fun addLogAdapter(adapter: LogAdapter) {
        adapters.add(adapter)
    }

    override fun clearLogAdapters() {
        adapters.clear()
    }

    override fun log(priority: Priority, tag: String?, message: String) {
        synchronized(this) {
            adapters.forEach {
                if (message.isJson) {
                    it.log(priority, tag, message.format)
                } else {
                    it.log(priority, tag, message)
                }
            }
        }
    }

    private val String.format: String
        get() = try {
            if (this.startsWith("{")) {
                JSONObject(this).toString(JSON_INDENT)
            } else if (this.startsWith("[")) {
                JSONArray(this).toString(JSON_INDENT)
            } else {
                this
            }
        } catch (e: JSONException) {
            this
        }

    private val String.isJson: Boolean
        get() {
            if (this.isEmpty()) return false

            return (this.startsWith("[") && this.endsWith("]")) || (this.startsWith("{") && this.endsWith(
                "}"
            ))
        }

    companion object {
        const val JSON_INDENT = 2
    }
}