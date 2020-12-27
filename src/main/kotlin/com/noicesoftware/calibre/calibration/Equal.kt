package com.noicesoftware.calibre.calibration

import java.math.BigDecimal

class Equal : IEquation {
    override fun calculate(value: BigDecimal) = value
}