package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property id The identifier of the connector.
 */
@Serializable
public data class SearchResultConnector(val id: String)