package com.noicesoftware.calibre

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalibreApplication

fun main(args: Array<String>) {
	runApplication<CalibreApplication>(*args)
}
