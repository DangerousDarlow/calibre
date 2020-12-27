package com.noicesoftware.calibre.calibration

import org.springframework.stereotype.Component

@Component
class MeasurementCalibrator {
    fun calibrate(deviceMeasurements: DeviceMeasurements): DeviceMeasurements {
        val deviceCalibration = deviceCalibrations[deviceMeasurements.device] ?: return deviceMeasurements
        val calibratedMeasurements = deviceMeasurements.measurements.mapValues { measurement ->
            calibrateMeasurement(
                measurement,
                deviceCalibration
            )
        }
        return deviceMeasurements.copy(measurements = calibratedMeasurements)
    }

    private fun calibrateMeasurement(
        measurement: Map.Entry<String, String>,
        deviceCalibration: DeviceCalibration
    ): String {
        val measurementCalibration = deviceCalibration[measurement.key] ?: return measurement.value
        val value = measurement.value.toBigDecimal()
        val calibrated = measurementCalibration.equationAtValue(value).calculate(value)
        return calibrated.toString()
    }

    private val deviceCalibrations = mutableMapOf<String, DeviceCalibration>()
}

typealias DeviceCalibration = Map<String, EquationProvider>