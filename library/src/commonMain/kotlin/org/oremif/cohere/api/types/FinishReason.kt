package org.oremif.cohere.api.types

import kotlinx.serialization.Serializable

/**
 * Enum representing the possible reasons for finishing a reply generation.
 *
 * - `COMPLETE` - the model sent back a finished reply
 * - `STOP_SEQUENCE` - indicates that the reply generation was halted by encountering a stop sequence
 * - `ERROR_LIMIT` - the reply was cut off because the model reached the maximum number of tokens for its context length
 * - `USER_CANCEL` - the reply generation was interrupted by the user
 * - `MAX_TOKENS` - the reply was cut off because the model reached the maximum number of tokens specified by the max_tokens parameter
 * - `ERROR` - something went wrong when generating the reply
 * - `ERROR_TOXIC` - the model generated a reply that was deemed toxic
 *
 * @property value The string representation of the finish reason.
 */
@Serializable
public enum class FinishReason(public val value: String) {
    /**
     * COMPLETE - the model sent back a finished reply
     */
    COMPLETE("COMPLETE"),

    /**
     * STOP_SEQUENCE - indicates that the reply generation was halted by encountering a stop sequence
     */
    STOP_SEQUENCE("STOP_SEQUENCE"),

    /**
     * ERROR - something went wrong when generating the reply
     */
    ERROR("ERROR"),

    /**
     * ERROR_TOXIC - the model generated a reply that was deemed toxic
     */
    ERROR_TOXIC("ERROR_TOXIC"),

    /**
     * ERROR_LIMIT - the reply was cut off because the model reached the maximum number of tokens for its context length
     */
    ERROR_LIMIT("ERROR_LIMIT"),

    /**
     * USER_CANCEL - the reply generation was interrupted by the user
     */
    USER_CANCEL("USER_CANCEL"),

    /**
     * MAX_TOKENS - the reply was cut off because the model reached the maximum number of tokens specified by the max_tokens parameter
     */
    MAX_TOKENS("MAX_TOKENS");

    public override fun toString(): String = this.value
}