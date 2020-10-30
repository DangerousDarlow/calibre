package com.noicesoftware.calibre.mqtt


import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MqttClientTemplate(val config: MqttConfig) {
    @Bean
    fun mqttClient(): MqttClient {
        val options = MqttConnectOptions()
        options.connectionTimeout = 10

        val client = MqttClient(config.brokerUrl, config.clientId)
        client.connect(options)
        return client
    }
}