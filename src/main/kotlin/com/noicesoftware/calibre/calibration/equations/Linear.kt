package com.noicesoftware.calibre.calibration.equations

import java.math.BigDecimal

/**
 * Uncalibrated and calibrated property values have a linear relationship
 */
class Linear(private val gradient: BigDecimal, private val offset: BigDecimal) : IEquation {
    override fun calculate(value: BigDecimal) = gradient * value + offset
}