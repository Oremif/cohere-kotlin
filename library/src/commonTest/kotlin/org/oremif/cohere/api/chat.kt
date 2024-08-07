package org.oremif.cohere.api

import kotlinx.coroutines.test.runTest
import org.oremif.cohere.api.types.ParameterDefinition
import org.oremif.cohere.api.types.Tool
import kotlin.test.Test

class ChatTests {

    private val token: String = "<<apiKey>>"
    private val cohere = Cohere(token) {
        clientName("snippet")
    }

    @Test
    fun `default chat request`() = runTest {
        val response = cohere.chat("What year was he born?") {
            chatHistory(
                listOf(
                    org.oremif.cohere.api.types.UserMessage("Who discovered gravity?"),
                    org.oremif.cohere.api.types.ChatBotMessage(
                        "The man who is widely credited" +
                            " with discovering gravity is" +
                            " Sir Isaac Newton"
                    )
                )
            )
        }

        println(response)
    }

    @Test
    fun `documents chat request`() = runTest {
        val response = cohere.chat("What year was he born?") {
            documents(
                listOf(
                    mapOf(
                        "title" to "CSPC: Backstreet Boys Popularity Analysis - ChartMasters",
                        "snippet" to """
                        ↓ Skip to Main Content

                        Music industry – One step closer to being accurate

                        CSPC: Backstreet Boys Popularity Analysis

                        Hernán Lopez Posted on February 9, 2017 Posted in CSPC 72 Comments Tagged with Backstreet Boys, Boy band

                        At one point, Backstreet Boys defined success: massive albums sales across the globe,
                        great singles sales, plenty of chart topping releases, hugely hyped tours and tremendous media coverage.

                        It is true that they benefited from extraordinarily good market conditions in all markets.
                        After all, the all-time record year for the music business, as far as revenues in billion dollars are concerned,
                        was actually 1999. That is, back when this five men group was at its peak.
                        """.trimIndent()
                    ),
                    mapOf(
                        "title" to "CSPC: NSYNC Popularity Analysis - ChartMasters",
                        "snippet" to """
                        ↓ Skip to Main Content

                        Music industry – One step closer to being accurate

                        CSPC: NSYNC Popularity Analysis

                        MJD Posted on February 9, 2018 Posted in CSPC 27 Comments Tagged with Boy band, N'Sync

                        At the turn of the millennium three teen acts were huge in the US, the Backstreet Boys,
                        Britney Spears and NSYNC. The latter is the only one we haven’t studied so far.
                        It took 15 years and Adele to break their record of 2.4 million units sold of No Strings Attached in its first week alone.

                        It wasn’t a fluke, as the second fastest selling album of the Soundscan era prior 2015,
                        was also theirs since Celebrity debuted with 1.88 million units sold.
                        """.trimIndent()
                    ),
                    mapOf(
                        "title" to "CSPC: Backstreet Boys Popularity Analysis - ChartMasters",
                        "snippet" to """
                        1997, 1998, 2000 and 2001 also rank amongst some of the very best years.

                        Yet the way many music consumers – especially teenagers and young women – embraced their output deserves its own chapter.
                        If Jonas Brothers and more recently One Direction reached a great level of popularity during the past decade,
                        the type of success achieved by Backstreet Boys is in a completely different level as they really dominated the business for a few years all over the world,
                        including in some countries that were traditionally hard to penetrate for Western artists.

                        We will try to analyze the extent of that hegemony with this new article with final results which will more than surprise many readers.
                        """.trimIndent()
                    ),
                    mapOf(
                        "title" to "CSPC: NSYNC Popularity Analysis - ChartMasters",
                        "snippet" to """
                        Was the teen group led by Justin Timberlake really that big?
                        Was it only in the US where they found success? Or were they a global phenomenon?

                        As usual, I’ll be using the Commensurate Sales to Popularity Concept in order to relevantly gauge their results.
                        This concept will not only bring you sales information for all NSYNC’s albums, physical and download singles,
                        as well as audio and video streaming, but it will also determine their true popularity.
                        If you are not yet familiar with the CSPC method, the next page explains it with a short video.
                        I fully recommend watching the video before getting into the sales figures.
                        """.trimIndent()
                    )
                )
            )
        }

        println(response)
    }

    @Test
    fun `streaming chat request`() = runTest {
        val response = cohere.chatStream("What year was he born?") {
            chatHistory(
                listOf(
                    org.oremif.cohere.api.types.UserMessage("Who discovered gravity?"),
                    org.oremif.cohere.api.types.ChatBotMessage(
                        "The man who is widely credited" +
                            " with discovering gravity is" +
                            " Sir Isaac Newton"
                    )
                )
            )
        }
    }

    @Test
    fun `tools chat request`() = runTest {
        val response = cohere.chat(
            "Can you provide a sales summary for 29th September 2023,"
                + " and also give me some details about the products in"
                + " the 'Electronics' category, for example their"
                + " prices and stock levels?"
        ) {
            tools(
                listOf(
                    Tool(
                        name = "query_daily_sales_report",
                        description = "Connects to a database to retrieve overall sales volumes and sales information for a given day."
                    ) {
                        "day" to ParameterDefinition(type = org.oremif.cohere.api.types.ParameterType.STRING) {
                            description("Retrieves sales data for this day, formatted as YYYY-MM-DD.")
                            required(true)
                        }
                    },
                    Tool(
                        name = "query_product_catalog",
                        description = "Connects to a product catalog with information about all the products being sold, including categories, prices, and stock levels."
                    ) {
                        "category" to ParameterDefinition(type = org.oremif.cohere.api.types.ParameterType.STRING) {
                            description("Retrieves product information data for all products in this category.")
                            required(true)
                        }
                    }
                )
            )
        }

        println(response)
    }
}