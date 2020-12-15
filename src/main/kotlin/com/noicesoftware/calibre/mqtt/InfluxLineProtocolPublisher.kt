package com.noicesoftware.calibre.mqtt

import com.noicesoftware.calibre.config.InfluxConfig
import com.noicesoftware.calibre.model.Measurements
import org.springframework.stereotype.Component

@Component
class InfluxLineProtocolPublisher(val influxConfig: InfluxConfig) {
    fun publish(measurements: Measurements) {

    }
}