package com.noicesoftware.calibre

import com.noicesoftware.calibre.mqtt.MqttConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(MqttConfig::class)
class CalibreApplication()

fun main(args: Array<String>) {
    runApplication<CalibreApplication>(*args)
}
