package com.doraemon.ftms.parser

/**
 * @author wye 11/7/24
 * @description
 */
object ParserFactory {
    @Suppress("UNCHECKED_CAST")
    fun <T> getParser(machineType: FitnessMachineType): MotionDataParser<T> {
        return when (machineType) {
            FitnessMachineType.INDOOR_BIKE -> IndoorBikeParser()
            FitnessMachineType.CROSS_TRAINER -> CrossTrainerParser()
        } as MotionDataParser<T>
    }
}