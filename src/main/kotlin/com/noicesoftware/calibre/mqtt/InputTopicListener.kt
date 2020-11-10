package com.noicesoftware.calibre.mqtt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.noicesoftware.calibre.config.MqttConfig
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.slf4j.Logger
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


@Component
class InputTopicListener(
        val mqttConfig: MqttConfig,
        val mqttClient: MqttClient,
        val objectMapper: ObjectMapper,
        val logger: Logger
) : IMqttMessageListener {

    @EventListener(ApplicationReadyEvent::class)
    fun onReady(event: ApplicationReadyEvent) = mqttClient.subscribe(mqttConfig.inputTopic, this)

    override fun messageArrived(topic: String?, message: MqttMessage?) {
        if (message == null)
            return

        try {
            val json = String(message.payload, Charsets.UTF_8)
            val measurements: SensorMeasurements = objectMapper.readValue(json)
        } catch (e: Exception) {
            logger.error("Failed to process sensor measurements: ${e.stackTraceToString()}")
        }
    }
}