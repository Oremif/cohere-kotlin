package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property name Name of the tool to call.
 * @property parameters The name and value of the parameters to use when invoking a tool.
 */
@Serializable
public data class ToolCall(
    public val name: String,
    public val parameters: Map<String, String>
)
