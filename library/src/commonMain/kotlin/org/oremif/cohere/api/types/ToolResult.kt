package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property call Contains the tool calls generated by the model. Use it to invoke your tools.
 * @property outputs
 */
@Serializable
public data class ToolResult(
    public val call: ToolCall,
    public val outputs: List<Map<String, String>>?
)