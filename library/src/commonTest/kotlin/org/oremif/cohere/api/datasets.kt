package org.oremif.cohere.api

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class DatasetsTests {

    private val token: String = "<<apiKey>>"
    private val cohere = Cohere(token) {
        clientName("snippet")
    }

    @Test
    fun `create a datasets`() = runTest {
        val response = cohere.datasets.create(
            name = "prompt-completion-dataset",
            type = DatasetsType.PROMPT_COMPLETION_FINETUNE_INPUT,
            data?
            )

        println(response)
    }

    @Test
    fun `get list datasets`() = runTest {
        val response = cohere.datasets.get("dataset_id")

        println(response)
    }

    @Test
    fun `get datasets usage`() = runTest {
        val response = cohere.datasets.getUsage()

        println(response)
    }

    @Test
    fun `get a dataset`() = runTest {
        val response = cohere.datasets.get("dataset_id")

        println(response)
    }

    @Test
    fun `delete a dataset`() = runTest {
        val response = cohere.datasets.delete("dataset_id")

        println(response)
    }
}