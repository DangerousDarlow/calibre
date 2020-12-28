package com.noicesoftware.calibre.calibration

import org.springframework.stereotype.Component
import java.math.BigDecimal

/**
 * Calibrates device measurements
 */
@Component
class MeasurementCalibrator(
    val calibrationProvider: DeviceCalibrationProvider
) {
    fun calibrate(deviceMeasurements: DeviceMeasurements): DeviceMeasurements {
        val deviceCalibration =
            calibrationProvider.calibrationForDevice(deviceMeasurements.device) ?: return deviceMeasurements

        val calibratedMeasurements =
            deviceMeasurements.measurements.mapValues { measurement -> calibrateMeasurement(measurement, deviceCalibration) }

        return deviceMeasurements.copy(measurements = calibratedMeasurements)
    }

    private fun calibrateMeasurement(
        measurement: Map.Entry<Property, BigDecimal>,
        deviceCalibration: DeviceCalibration
    ): BigDecimal {
        val equation = deviceCalibration.forPropertyAtValue(measurement.key, measurement.value)
        return equation.calculate(measurement.value)
    }
}