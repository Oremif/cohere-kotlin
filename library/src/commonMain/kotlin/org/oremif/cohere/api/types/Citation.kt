package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property start The index of text that the citation starts at, counting from zero.
 * For example, a generation of `Hello, world!` with a citation on `world` would have a start value of `7`.
 * This is because the citation starts at `w`, which is the seventh character.
 * @property end The index of text that the citation ends after, counting from zero.
 * For example, a generation of `Hello, world!` with a citation on `world` would have an end value of `11`.
 * This is because the citation ends after `d`, which is the eleventh character.
 * @property text The text of the citation.
 * For example, a generation of `Hello, world!` with a citation of `world` would have a text value of `world`.
 * @property documentIds Identifiers of documents cited by this section of the generated reply.
 */
@Serializable
public data class Citation(
    val start: Int,
    val end: Int,
    val text: String,
    val documentIds: List<String>
)