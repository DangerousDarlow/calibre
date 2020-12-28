package com.noicesoftware.calibre.calibration

import org.springframework.stereotype.Component

@Component
class DeviceCalibrationProvider {
    fun forDevice(device: String): DeviceCalibration? {
        return null
    }
}