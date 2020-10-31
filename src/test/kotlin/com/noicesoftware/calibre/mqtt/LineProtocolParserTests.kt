package com.noicesoftware.calibre.mqtt

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class LineProtocolParserTests {
    @Test
    fun testIt() {
        // example line protocol plaintext
        // environment/temperature,location=living\\ room temperature=16.7,humidity=50

        assertThat(2).isEqualTo(2)
    }
}