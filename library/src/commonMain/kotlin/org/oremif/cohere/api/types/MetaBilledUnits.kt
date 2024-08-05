package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property inputTokens The number of billed input tokens.
 * @property outputTokens The number of billed output tokens.
 * @property searchUnits The number of billed search units.
 * @property classifications The number of billed classifications units.
 */
@Serializable
public data class MetaBilledUnits(
    val inputTokens: Double? = null,
    val outputTokens: Double? = null,
    val searchUnits: Double? = null,
    val classifications: Double? = null
)