package com.noicesoftware.calibre.calibration

import com.noicesoftware.calibre.calibration.equations.Equal
import com.noicesoftware.calibre.calibration.equations.IEquation
import com.noicesoftware.calibre.calibration.equations.Offset
import com.noicesoftware.calibre.http.CalibrationDataSet
import com.noicesoftware.calibre.http.Values
import java.math.BigDecimal

/**
 * Calibration relationships for multiple properties for a single device
 */
class DeviceCalibration {

    /**
     * Relationship for a property as a given uncalibrated value
     */
    fun forPropertyAtValue(property: Property, value: BigDecimal): IEquation {
        val equations = propertyEquations[property] ?: emptyList()
        return when (equations.size) {
            1 -> equations.first()
            else -> Equal()
        }
    }

    fun addCalibrationDataSet(dataSet: CalibrationDataSet) {
        propertyCalibrationData
            .getOrPut(dataSet.property) { mutableListOf() }
            .addAll(dataSet.values)

        calculatePropertyEquations(dataSet.property)
    }

    private fun calculatePropertyEquations(property: Property) {
        val data = propertyCalibrationData[property] ?: emptyList()
        when (data.size) {
            0 -> propertyEquations.remove(property)
            1 -> {
                val dataPoint = data.first()
                propertyEquations[property] = listOf(Offset(dataPoint.actual - dataPoint.measured))
            }
        }
    }

    private val propertyCalibrationData = mutableMapOf<Property, MutableList<Values>>()

    private val propertyEquations = mutableMapOf<Property, List<IEquation>>()
}