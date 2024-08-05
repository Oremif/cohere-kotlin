package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property apiVersion
 * @property billedUnits
 * @property tokens
 * @property warnings
 */
@Serializable
public data class MetaInfo(
    val apiVersion: MetaApiVersion? = null,
    val billedUnits: MetaBilledUnits? = null,
    val tokens: MetaTokens? = null,
    val warnings: List<String>? = null
)