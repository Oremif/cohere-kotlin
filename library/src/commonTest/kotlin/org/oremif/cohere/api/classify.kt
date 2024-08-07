package org.oremif.cohere.api

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ClassifyTests {

    private val token: String = "<<apiKey>>"
    private val cohere = Cohere(token) {
        clientName("snippet")
    }

    @Test
    fun `default classify request`() = runTest {
        val response = cohere.classify(inputs = listOf("Confirm your email address", "hey i need u to send some $")) {
            examples {
                add(text = "Dermatologists don't like her!", label = "Spam")
                add(text = "'Hello, open to this?'", label = "Spam")
                add(text = "I need help please wire me $1000 right now", label = "Spam")
                add(text = "Nice to know you ;)", label = "Spam")
                add(text = "Please help me?", label = "Spam")
                add(text = "Your parcel will be delivered today", label = "Not spam")
                add(text = "Review changes to our Terms and Conditions", label = "Not spam")
                add(text = "Weekly sync notes", label = "Not spam")
                add(text = "'Re: Follow up from today's meeting'", label = "Not spam")
                add(text = "Pre-read for tomorrow", label = "Not spam")
            }
        }

        println(response)
    }
}