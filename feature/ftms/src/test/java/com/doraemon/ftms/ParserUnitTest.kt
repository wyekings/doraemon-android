package com.doraemon.ftms

import com.doraemon.ftms.parser.FitnessMachineType
import com.doraemon.ftms.parser.ParserFactory
import com.doraemon.ftms.parser.data.IndoorBikeData
import kotlin.test.Test

/**
 * @author wye 11/7/24
 * @description
 */
class ParserUnitTest {

    @Test
    fun parseData() {
        val data = byteArrayOf()
        val parser = ParserFactory.getParser<IndoorBikeData>(FitnessMachineType.INDOOR_BIKE)
        parser.parse(data)
    }
}