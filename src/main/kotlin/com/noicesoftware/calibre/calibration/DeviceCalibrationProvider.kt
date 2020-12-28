package com.noicesoftware.calibre.calibration

import com.noicesoftware.calibre.http.CalibrationDataSet
import org.springframework.stereotype.Component

@Component
class DeviceCalibrationProvider {
    fun calibrationForDevice(device: String) = deviceCalibrations[device]

    fun addCalibrationDataSet(device: String, dataSet: CalibrationDataSet) {
        deviceCalibrations
            .getOrPut(device) { DeviceCalibration() }
            .addCalibrationDataSet(dataSet)
    }

    fun clear(device: String) {
        deviceCalibrations.remove(device)
    }

    private val deviceCalibrations = mutableMapOf<String, DeviceCalibration>()
}