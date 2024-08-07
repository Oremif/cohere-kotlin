package org.oremif.cohere.api

import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class EmbedJobsTests {

    private val token: String = "<<apiKey>>"
    private val cohere = Cohere(token) {
        clientName("snippet")
    }

    @Test
    fun `create embed job`() = runTest {
        val response = cohere.embedJobs.create(
            datasetId = "ds.id",
            model = "embed-english-v3.0",
            inputType = EmbedInputType.SEARCH_DOCUMENT
        )

        println(response)
    }

    @Test
    fun `get list embed jobs`() = runTest {
        val response = cohere.embedJobs.list()

        println(response)
    }

    @Test
    fun `fetch an embed job`() = runTest {
        val response = cohere.embedJobs.get("job_id")

        println(response)
    }

    @Test
    fun `cancel an embed job`() = runTest {
        val response = cohere.embedJobs.cancel("job_id")

        println(response)
    }
}