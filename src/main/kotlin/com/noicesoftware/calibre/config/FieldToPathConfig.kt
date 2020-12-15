package com.noicesoftware.calibre.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * Maps fields in data received from sensors to InfluxDb paths
 */
@ConstructorBinding
@ConfigurationProperties("influx")
data class FieldToPathConfig(
        val mappings: Array<FieldToPathMapping>
)

data class FieldToPathMapping (
        val path: String,
        val fields: Array<String>
)