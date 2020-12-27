package com.noicesoftware.calibre.calibration

data class DeviceMeasurements(
        val device: String,
        val measurements: Map<String, String>
)