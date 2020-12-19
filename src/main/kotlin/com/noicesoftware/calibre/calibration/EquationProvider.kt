package com.noicesoftware.calibre.calibration

class EquationProvider {
    /**
     * Given an uncalibrated value return an equation defining the relationship to the calibrated value
     */
    fun equation(value: Double): IEquation {
        return Equal()
    }
}