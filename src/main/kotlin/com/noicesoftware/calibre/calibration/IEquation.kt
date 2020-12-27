package com.noicesoftware.calibre.calibration

import java.math.BigDecimal

interface IEquation {
    fun calculate(value: BigDecimal): BigDecimal
}