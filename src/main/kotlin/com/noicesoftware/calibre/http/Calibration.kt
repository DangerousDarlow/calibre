package com.noicesoftware.calibre.http

data class Calibration(
    val device: String,
    val property: String,
    val values: Array<Values>
)