package com.noicesoftware.calibre.mqtt

data class SensorMeasurements(
        val sensor: String,
        val measurements: Map<String, String>
)