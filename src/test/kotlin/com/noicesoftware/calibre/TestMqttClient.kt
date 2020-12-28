package com.noicesoftware.calibre

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.noicesoftware.calibre.calibration.DeviceMeasurements
import com.noicesoftware.calibre.config.MqttConfig
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Component
class TestMqttClient(
    private val mqttConfig: MqttConfig,
    private val objectMapper: ObjectMapper
) : IMqttMessageListener {

    private val mqttClient: MqttClient

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()
    private val queue = ConcurrentLinkedQueue<DeviceMeasurements>()

    init {
        val options = MqttConnectOptions()
        options.connectionTimeout = 10

        mqttClient = MqttClient(mqttConfig.brokerUrl, "calibre-application-tests")
        mqttClient.connect(options)
    }

    override fun messageArrived(topic: String?, message: MqttMessage?) = lock.withLock {
        if (message != null) {
            val json = String(message.payload, Charsets.UTF_8)
            val measurements: DeviceMeasurements = objectMapper.readValue(json)
            queue.add(measurements)
        }

        condition.signalAll()
    }

    fun clearOutputQueue() = lock.withLock {
        queue.clear()
    }

    fun popOutputQueue(): DeviceMeasurements = lock.withLock {
        if (queue.isEmpty())
            condition.await()

        return queue.poll()
    }

    fun subscribeToOutput() {
        mqttClient.subscribe(mqttConfig.outputTopic, this)
    }

    fun publishToInput(deviceMeasurements: DeviceMeasurements) {
        val str = objectMapper.writeValueAsString(deviceMeasurements)

        // Don't use MqttClient.publish because it will block waiting for a
        // response which will never come as this is executing in the read thread
        mqttClient.getTopic(mqttConfig.inputTopic).publish(str.toByteArray(), 0, false)
    }
}