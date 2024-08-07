package org.oremif.cohere.api

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class EmbedTests {

    private val token: String = "<<apiKey>>"
    private val cohere = Cohere(token) {
        clientName("snippet")
    }

    @Test
    fun `default embed request`() = runTest {
        val response = cohere.embed(texts = listOf("hello", "goodbye")) {
            model("embed-english-v3.0")
            inputType(EmbedInputType.CLASSIFICATION)
        }

        println(response)
    }
}