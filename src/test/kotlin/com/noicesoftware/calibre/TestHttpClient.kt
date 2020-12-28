package com.noicesoftware.calibre

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.noicesoftware.calibre.http.CalibrationDataSet
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class TestHttpClient(private val restTemplate: TestRestTemplate) {
    var port: Int = 0

    private fun host(): String = "http://localhost:$port"

    fun postCalibrationData(device: String, calibrationDataSet: CalibrationDataSet) {
        val response = restTemplate.postForEntity("${host()}/calibration/${device}/add", calibrationDataSet, Void::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.ACCEPTED)
    }

    fun clearCalibrationData(device: String) {
        val response = restTemplate.postForEntity("${host()}/calibration/${device}/clear", null, Void::class.java)
        assertThat(response.statusCode).isEqualTo(HttpStatus.ACCEPTED)
    }
}