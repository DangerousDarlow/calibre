package com.noicesoftware.calibre.calibration.equations

import java.math.BigDecimal

interface IEquation {
    fun calculate(value: BigDecimal): BigDecimal
}