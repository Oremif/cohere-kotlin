package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property searchQuery The generated search query. Contains the text of the query and a unique identifier for the query.
 * @property connector The connector from which this result comes from.
 * @property documentIds Identifiers of documents found by this search query.
 * @property errorMessage An error message if the search failed.
 * @property continueOnFailure Whether a chat request should continue or not if the request to this connector fails.
 */
@Serializable
public data class SearchResult(
    val searchQuery: SearchQuery? = null,
    val connector: SearchResultConnector,
    val documentIds: List<String>,
    val errorMessage: String? = null,
    val continueOnFailure: Boolean? = null,
)