package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property inputTokens The number of tokens used as input to the model.
 * @property outputTokens The number of tokens produced by the model.
 */
@Serializable
public data class MetaTokens(
    val inputTokens: Double? = null,
    val outputTokens: Double? = null
)