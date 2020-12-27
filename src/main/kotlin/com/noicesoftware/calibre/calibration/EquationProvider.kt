package com.noicesoftware.calibre.calibration

import java.math.BigDecimal

class EquationProvider {
    /**
     * Given an uncalibrated value return an equation defining the relationship to the calibrated value
     */
    fun equationAtValue(value: BigDecimal): IEquation {
        return Equal()
    }
}