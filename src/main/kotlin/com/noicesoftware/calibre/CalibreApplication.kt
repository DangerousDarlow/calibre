package com.noicesoftware.calibre

import com.noicesoftware.calibre.config.FieldToPathConfig
import com.noicesoftware.calibre.config.MqttConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(MqttConfig::class, FieldToPathConfig::class)
class CalibreApplication

fun main(args: Array<String>) {
    runApplication<CalibreApplication>(*args)
}
