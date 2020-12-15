package com.noicesoftware.calibre.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("influx")
data class InfluxConfig(
        val mappings: Array<Mapping>
)

data class Mapping (
        val path: String,
        val fields: Array<String>
)