package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

@Serializable
public enum class CitationQuality(public val value: String) {
    FAST("fast"),
    ACCURATE("accurate"),
    OFF("off");

    public override fun toString(): String = this.value
}
