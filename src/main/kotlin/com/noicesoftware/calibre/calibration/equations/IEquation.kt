package com.noicesoftware.calibre.calibration.equations

import java.math.BigDecimal

/**
 * Relationship between uncalibrated and calibrated property value
 */
interface IEquation {
    fun calculate(value: BigDecimal): BigDecimal
}