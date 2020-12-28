package com.noicesoftware.calibre.calibration.equations

import java.math.BigDecimal

/**
 * Uncalibrated and calibrated property values are equal
 */
class Equal : IEquation {
    override fun calculate(value: BigDecimal) = value
}