package com.noicesoftware.calibre.calibration.equations

import java.math.BigDecimal

class Linear(private val gradient: BigDecimal, private val offset: BigDecimal) : IEquation {
    override fun calculate(value: BigDecimal) = gradient * value + offset
}