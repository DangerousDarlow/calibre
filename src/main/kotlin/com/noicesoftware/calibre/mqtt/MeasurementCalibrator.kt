package com.noicesoftware.calibre.mqtt

import com.noicesoftware.calibre.model.Measurements
import org.springframework.stereotype.Component

@Component
class MeasurementCalibrator {
    fun calibrate(measurements: Measurements): Measurements {
        return measurements
    }
}