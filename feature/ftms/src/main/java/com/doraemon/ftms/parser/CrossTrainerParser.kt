package com.doraemon.ftms.parser

import com.doraemon.ftms.parser.data.CrossTrainerData
import com.doraemon.ftms.parser.data.ParsedData

/**
 * @author wye 11/7/24
 * @description
 */
class CrossTrainerParser: MotionDataParser<CrossTrainerData> {

    private val offset = 0

    override fun parse(data: ByteArray): ParsedData<CrossTrainerData> {
        val flags = (data[offset].toInt() and 0xFF) or ((data[offset + 1].toInt() and 0xFF) shl 8)

        val speed = if ((flags and (1 shl 0)) != 0) {
            ((data[offset + 2].toInt() and 0xFF) or ((data[offset + 3].toInt() and 0xFF) shl 8)) / 100f
        } else null

        val cadence = if ((flags and (1 shl 1)) != 0) {
            ((data[offset + 4].toInt() and 0xFF) or ((data[offset + 5].toInt() and 0xFF) shl 8)) / 2f
        } else null

        return ParsedData(CrossTrainerData(speed= speed,cadence = cadence ))
    }
}