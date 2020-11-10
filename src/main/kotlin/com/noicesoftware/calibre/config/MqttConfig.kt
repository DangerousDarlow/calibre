package com.noicesoftware.calibre.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("mqtt")
data class MqttConfig(
        val brokerUrl: String,
        val clientId: String,
        val inputTopic: String,
        val outputTopic: String
)