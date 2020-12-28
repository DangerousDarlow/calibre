package com.noicesoftware.calibre.calibration.equations

import java.math.BigDecimal

/**
 * Uncalibrated and calibrated property values are offset by a constant amount
 */
class Offset(private val offset: BigDecimal) : IEquation {
    override fun calculate(value: BigDecimal) = value + offset
}