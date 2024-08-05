package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

public fun ParameterDefinition(type: ParameterType, body: ParameterDefinition.Builder.() -> Unit): ParameterDefinition =
    ParameterDefinition.Builder(type).apply(body).build()

/**
 * @property description The description of the parameter.
 *
 * @property type The type of the parameter. Must be a valid Python type.
 *
 * @property required Defaults to false.
 * Denotes whether the parameter is always present (required) or not. Defaults to not required.
 */
@Serializable
public data class ParameterDefinition(
    val description: String? = null,
    val type: ParameterType,
    val required: Boolean? = null
) {
    public class Builder internal constructor(private val type: ParameterType) {
        private var description: String? = null
        private var required: Boolean? = null

        public fun description(description: String): Unit = let { this.description = description }

        public fun required(required: Boolean): Unit = let { this.required = required }

        public fun build(): ParameterDefinition =
            ParameterDefinition(type = type, description = description, required = required)
    }
}