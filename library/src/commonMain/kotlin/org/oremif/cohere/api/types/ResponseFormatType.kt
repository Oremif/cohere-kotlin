package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

@Serializable
public enum class ResponseFormatType(public val value: String) {
    TEXT("text"),

    JSON_OBJECT("json_object");


    public override fun toString(): String = this.value
}