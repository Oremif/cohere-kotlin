package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

@Serializable
public data class MetaApiVersion(
    val version: String,
    val isDeprecated: Boolean? = null,
    val isExperimental: Boolean? = null
)