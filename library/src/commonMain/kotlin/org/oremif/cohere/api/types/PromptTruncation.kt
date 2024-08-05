package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

@Serializable
public enum class PromptTruncation(public val value: String) {
    AUTO("AUTO"),
    OFF("OFF"),
    AUTO_PRESERVE_ORDER("AUTO_PRESERVE_ORDER");

    public override fun toString(): String = this.value
}