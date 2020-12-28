package com.noicesoftware.calibre.mqtt

import com.fasterxml.jackson.databind.ObjectMapper
import com.noicesoftware.calibre.config.FieldToPathConfig
import com.noicesoftware.calibre.config.MqttConfig
import com.noicesoftware.calibre.calibration.DeviceMeasurements
import org.eclipse.paho.client.mqttv3.MqttClient
import org.springframework.stereotype.Component

@Component
class OutputTopicPublisher(
    val fieldToPathConfig: FieldToPathConfig,
    val mqttConfig: MqttConfig,
    val mqttClient: MqttClient,
    val objectMapper: ObjectMapper
) {
    fun publish(deviceMeasurements: DeviceMeasurements) {
        val str = objectMapper.writeValueAsString(deviceMeasurements)

        // Don't use MqttClient.publish because it will block waiting for a
        // response which will never come as this is executing in the read thread
        mqttClient.getTopic(mqttConfig.outputTopic).publish(str.toByteArray(), 0, false)
    }
}