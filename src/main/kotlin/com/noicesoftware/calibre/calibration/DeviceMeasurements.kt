package com.noicesoftware.calibre.calibration

import java.math.BigDecimal

/**
 * Property measurements from a single device
 */
data class DeviceMeasurements(
        val device: String,
        val measurements: Map<Property, BigDecimal>
)