package com.noicesoftware.calibre.http

import com.noicesoftware.calibre.calibration.DeviceCalibrationProvider
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CalibrationController(val deviceCalibrationProvider: DeviceCalibrationProvider) {

    @PostMapping("calibrate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun calibrate(@RequestBody dataSet: CalibrationDataSet) {
        deviceCalibrationProvider.addCalibrationDataSet(dataSet)
    }
}