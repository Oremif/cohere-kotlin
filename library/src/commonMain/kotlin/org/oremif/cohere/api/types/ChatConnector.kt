package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * @property id The identifier of the connector.
 * @property userAccessToken When specified, this user access token will be passed to the connector in the Authorization header instead of the Cohere generated one.
 * @property continueOnFailure Defaults to `false`.
 *
 * When `true`, the request will continue if this connector returned an error.
 * @property options Provides the connector with different settings at request time.
 * The key/value pairs of this object are specific to each connector.
 *
 * For example, the connector `web-search` supports the `site` option, which limits search results to the specified domain.
 */
@Serializable
public data class ChatConnector(
    val id: String,
    val userAccessToken: String? = null,
    val continueOnFailure: Boolean? = null,
    val options: Map<String, String>? = null,
)