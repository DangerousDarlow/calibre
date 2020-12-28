package com.noicesoftware.calibre.http

import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CalibrationController(val logger: Logger) {

    @PostMapping("calibrate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun calibrate(@RequestBody calibration: Calibration) {
        logger.info(calibration.toString())
    }
}