package com.noicesoftware.calibre.mqtt

import org.eclipse.paho.client.mqttv3.MqttClient
import org.slf4j.Logger
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class CalibrationService(val mqttConfig: MqttConfig, val mqttClient: MqttClient, val logger: Logger) {
    @EventListener(ApplicationReadyEvent::class)
    fun onReady(event: ApplicationReadyEvent) {
        mqttClient.subscribe(mqttConfig.inputTopic) { topic, message -> logger.info("$topic $message") }
    }
}