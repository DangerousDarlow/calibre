package com.noicesoftware.calibre.http

import com.noicesoftware.calibre.calibration.DeviceCalibrationProvider
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("calibration")
class CalibrationController(val deviceCalibrationProvider: DeviceCalibrationProvider) {

    @PostMapping("{device}/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun addCalibrationDataSet(@PathVariable device: String, @RequestBody dataSet: CalibrationDataSet) {
        deviceCalibrationProvider.addCalibrationDataSet(device, dataSet)
    }

    @PostMapping("{device}/clear")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun clearCalibrationForDevice(@PathVariable device: String) {
        deviceCalibrationProvider.clear(device)
    }
}