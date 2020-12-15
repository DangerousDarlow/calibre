package com.noicesoftware.calibre.mqtt

import com.fasterxml.jackson.databind.ObjectMapper
import com.noicesoftware.calibre.config.FieldToPathConfig
import com.noicesoftware.calibre.config.MqttConfig
import com.noicesoftware.calibre.model.Measurements
import org.eclipse.paho.client.mqttv3.MqttClient
import org.springframework.stereotype.Component

@Component
class OutputTopicPublisher(
    val fieldToPathConfig: FieldToPathConfig,
    val mqttConfig: MqttConfig,
    val mqttClient: MqttClient,
    val objectMapper: ObjectMapper
) {
    fun publish(measurements: Measurements) {
        val str = objectMapper.writeValueAsString(measurements)
        mqttClient.publish(mqttConfig.outputTopic, str.toByteArray(), 0, false)
    }
}