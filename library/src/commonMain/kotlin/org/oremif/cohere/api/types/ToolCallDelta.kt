package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property name Name of the tool call
 * @property index Index of the tool call generated
 * @property parameters Chunk of the tool parameters
 * @property text Chunk of the tool plan text
 */
@Serializable
public data class ToolCallDelta(
    val name: String? = null,
    val index: Int? = null,
    val parameters: String? = null,
    val text: String? = null
)