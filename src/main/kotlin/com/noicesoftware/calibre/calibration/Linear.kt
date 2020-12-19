package com.noicesoftware.calibre.calibration

class Linear(private val gradient: Double, private val offset: Double) : IEquation {
    override fun calculate(value: Double) = gradient * value + offset
}