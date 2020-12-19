package com.noicesoftware.calibre.calibration

import org.springframework.stereotype.Component

@Component
class MeasurementCalibrator {
    fun calibrate(measurements: Measurements): Measurements {
        if (!deviceCalibration.containsKey(measurements.device))
            return measurements

        return measurements
    }

    private val deviceCalibration = mutableMapOf<String, DeviceCalibration>()
}

typealias DeviceCalibration = Map<String, EquationProvider>