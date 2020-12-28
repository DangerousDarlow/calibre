package com.noicesoftware.calibre.http

import com.noicesoftware.calibre.calibration.Property

data class CalibrationDataSet(
    val property: Property,
    val values: Array<Values>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CalibrationDataSet

        if (property != other.property) return false
        if (!values.contentEquals(other.values)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = property.hashCode()
        result = 31 * result + values.contentHashCode()
        return result
    }
}