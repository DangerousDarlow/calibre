package com.noicesoftware.calibre.calibration.equations

import java.math.BigDecimal

class Offset(private val offset: BigDecimal) : IEquation {
    override fun calculate(value: BigDecimal) = value + offset
}