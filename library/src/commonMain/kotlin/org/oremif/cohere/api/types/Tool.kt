package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

public fun Tool(name: String, description: String, body: Tool.Builder.() -> Unit): Tool =
    Tool.Builder(name, description).apply(body).build()

/**
 * @property name The name of the tool to be called.
 * Valid names contain only the characters `a-z`, `A-Z`, `0-9`, `_` and must not begin with a digit.
 * @property description The description of what the tool does,
 * the model uses the description to choose when and how to call the function.
 * @property parameterDefinitions The input parameters of the tool.
 * Accepts a dictionary where the key is the name of the parameter and the value is the parameter spec.
 * Valid parameter names contain only the characters `a-z`, `A-Z`, `0-9`, `_` and must not begin with a digit.
 */
@Serializable
public data class Tool(
    val name: String,
    val description: String,
    val parameterDefinitions: ParameterDefinition? = null
) {

    public class Builder internal constructor(private val name: String, private val description: String) {
        private var parameterDefinitions: ParameterDefinition? = null

        public fun parameterDefinitions(parameterDefinitions: ParameterDefinition): Unit = let {
            this.parameterDefinitions = parameterDefinitions
        }

        public fun build(): Tool = Tool(name, description, parameterDefinitions)
    }
}
