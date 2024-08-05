@file:OptIn(ExperimentalSerializationApi::class)

package org.oremif.cohere.api.types

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator


/**
 * Represents a single message in the chat history, excluding the current user turn.
 * It has two properties: `role` and `message`.
 * The `role` identifies the sender (`CHATBOT`, `SYSTEM`, or `USER`), while the message contains the text content.
 *
 * The chat_history parameter should not be used for `SYSTEM` messages in most cases.
 * Instead, to add a `SYSTEM` role message at the beginning of a conversation, the `preamble` parameter should be used.
 *
 * @see ChatBotMessage
 * @see SystemMessage
 * @see UserMessage
 * @see ToolMessage
 */
@Serializable
@JsonClassDiscriminator("role")
public sealed interface Message


/**
 * Represents a single message in the chat history, excluding the current user turn.
 * It has two properties: `role` and `message`.
 * The `role` identifies the sender (`CHATBOT`, `SYSTEM`, or `USER`), while the message contains the text content.
 *
 * The chat_history parameter should not be used for `SYSTEM` messages in most cases.
 * Instead, to add a `SYSTEM` role message at the beginning of a conversation, the `preamble` parameter should be used.
 *
 * **role** - CHATBOT
 * @property message Contents of the chat message.
 * @property toolCalls
 */
@JsonClassDiscriminator("CHATBOT")
public data class ChatBotMessage(val message: String, val toolCalls: List<ToolCall>? = null) : Message


/**
 * Represents a single message in the chat history, excluding the current user turn.
 * It has two properties: `role` and `message`.
 * The `role` identifies the sender (`CHATBOT`, `SYSTEM`, or `USER`), while the message contains the text content.
 *
 * The chat_history parameter should not be used for `SYSTEM` messages in most cases.
 * Instead, to add a `SYSTEM` role message at the beginning of a conversation, the `preamble` parameter should be used.
 *
 * **role** - SYSTEM
 * @property message Contents of the chat message.
 * @property toolCalls
 */
@JsonClassDiscriminator("SYSTEM")
public data class SystemMessage(val message: String, val toolCalls: List<ToolCall>? = null) : Message

/**
 * Represents a single message in the chat history, excluding the current user turn.
 * It has two properties: `role` and `message`.
 * The `role` identifies the sender (`CHATBOT`, `SYSTEM`, or `USER`), while the message contains the text content.
 *
 * The chat_history parameter should not be used for `SYSTEM` messages in most cases.
 * Instead, to add a `SYSTEM` role message at the beginning of a conversation, the `preamble` parameter should be used.
 *
 * **role** - USER
 * @property message Contents of the chat message.
 * @property toolCalls
 */
@JsonClassDiscriminator("USER")
public data class UserMessage(val message: String, val toolCalls: List<ToolCall>? = null) : Message


/**
 * Represents tool result in the chat history.
 *
 * **role** - TOOL
 * @property toolResults
 */
@JsonClassDiscriminator("TOOL")
public data class ToolMessage(val toolResults: List<ToolResult>? = null) : Message