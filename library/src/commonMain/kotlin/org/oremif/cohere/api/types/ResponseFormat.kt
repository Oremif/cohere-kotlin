package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

@Serializable
public data class ResponseFormat(
    val type: ResponseFormatType,
    val schema: Map<String, String>? = null
)