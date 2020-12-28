package com.noicesoftware.calibre.calibration

import com.noicesoftware.calibre.http.CalibrationDataSet
import org.springframework.stereotype.Component

@Component
class DeviceCalibrationProvider {
    fun calibrationForDevice(device: String) = deviceCalibrations[device]

    fun addCalibrationDataSet(dataSet: CalibrationDataSet) {
        deviceCalibrations
            .getOrPut(dataSet.device) { DeviceCalibration() }
            .addCalibrationDataSet(dataSet)
    }

    private val deviceCalibrations = mutableMapOf<String, DeviceCalibration>()
}