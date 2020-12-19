package com.noicesoftware.calibre.calibration

data class Measurements(
        val device: String,
        val measurements: Map<String, String>
)