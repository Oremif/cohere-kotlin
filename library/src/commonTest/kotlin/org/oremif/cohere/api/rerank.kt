package org.oremif.cohere.api

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class RerankTests {

    private val token: String = "<<apiKey>>"
    private val cohere = Cohere(token) {
        clientName("snippet")
    }

    @Test
    fun `default embed request`() = runTest {
        val response = cohere.rerank(
            query = "What is the capital of the United States?",
            documents = listOf(
                Document("Carson City is the capital city of the American state of Nevada."),
                Document("The Commonwealth of the Northern Mariana Islands is a group of islands in the Pacific Ocean. Its capital is Saipan."),
                Document("Capitalization or capitalisation in English grammar is the use of a capital letter at the start of a word. English usage varies from capitalization in other languages."),
                Document("Washington, D.C. (also known as simply Washington or D.C., and officially as the District of Columbia) is the capital of the United States. It is a federal district."),
                Document("Capital punishment (the death penalty) has existed in the United States since beforethe United States was a country. As of 2017, capital punishment is legal in 30 of the 50 states.")
            ) {
                model("rerank-english-v3.0")
                topN(3)
            }
        )
        
        println(response)
    }
}