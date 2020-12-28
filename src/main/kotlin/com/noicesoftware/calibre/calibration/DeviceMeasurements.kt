package com.noicesoftware.calibre.calibration

import java.math.BigDecimal

data class DeviceMeasurements(
        val device: String,
        val measurements: Map<Property, BigDecimal>
)