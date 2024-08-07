package org.oremif.cohere.api.core

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

public object Environment {
    public const val PRODUCTION_URL: String = "https://api.cohere.com/v1"

    @OptIn(ExperimentalSerializationApi::class)
    public val JSON_CONFIG: Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
        decodeEnumsCaseInsensitive = true
        namingStrategy = JsonNamingStrategy.SnakeCase
    }
}