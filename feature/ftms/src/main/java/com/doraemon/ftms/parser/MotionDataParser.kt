package com.doraemon.ftms.parser

import com.doraemon.ftms.parser.data.ParsedData

/**
 * @author wangpt 11/7/24
 * @description
 */
interface MotionDataParser<T> {
    fun parse(data: ByteArray): ParsedData<T>
}