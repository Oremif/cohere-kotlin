package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property text The text of the search query.
 * @property generationId Unique identifier for the generated search query. Useful for submitting feedback.
 */
@Serializable
public data class SearchQuery(
    val text: String,
    val generationId: String
)