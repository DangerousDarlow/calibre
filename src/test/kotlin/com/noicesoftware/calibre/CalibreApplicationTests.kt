package com.noicesoftware.calibre

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.noicesoftware.calibre.calibration.DeviceMeasurements
import com.noicesoftware.calibre.http.CalibrationDataSet
import com.noicesoftware.calibre.http.Values
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalibreApplicationTests {
    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var httpClient: TestHttpClient

    @Autowired
    lateinit var mqttClient: TestMqttClient

    @BeforeEach
    fun setPort() {
        httpClient.port = port
    }

    @BeforeEach
    fun subscribe() {
        mqttClient.subscribeToOutput()
        mqttClient.clearOutputQueue()
    }

    val device = "CalibreApplicationTests"

    /**
     * A single calibration point is provided. Calibrated values are a fixed offset from measured values.
     */
    @Test
    fun single_calibration_point() {
        httpClient.postCalibrationData(
            CalibrationDataSet(
                device = device,
                property = "temperature",
                values = arrayOf(Values(measured = BigDecimal(10), actual = BigDecimal(22.5)))
            )
        )

        mqttClient.publishToInput(
            DeviceMeasurements(
                device = device,
                measurements = mapOf("temperature" to BigDecimal(8))
            )
        )

        val deviceMeasurements = mqttClient.popOutputQueue()
        assertThat(deviceMeasurements.device).isEqualTo(device)
        assertThat(deviceMeasurements.measurements["temperature"]).isEqualTo(BigDecimal(20.5))
    }
}
