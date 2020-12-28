package com.noicesoftware.calibre.calibration

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class MeasurementCalibrator(
    val calibrationProvider: DeviceCalibrationProvider
) {
    fun calibrate(deviceMeasurements: DeviceMeasurements): DeviceMeasurements {
        val calibration = calibrationProvider.forDevice(deviceMeasurements.device) ?: return deviceMeasurements

        val calibratedMeasurements =
            deviceMeasurements.measurements.mapValues { measurement -> calibrateMeasurement(measurement, calibration) }

        return deviceMeasurements.copy(measurements = calibratedMeasurements)
    }

    private fun calibrateMeasurement(
        measurement: Map.Entry<Property, BigDecimal>,
        calibration: DeviceCalibration
    ): BigDecimal {
        val equation = calibration.forPropertyAtValue(measurement.key, measurement.value) ?: return measurement.value
        return equation.calculate(measurement.value)
    }
}