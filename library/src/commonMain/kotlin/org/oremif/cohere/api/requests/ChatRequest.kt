package org.oremif.cohere.api.requests

import kotlinx.serialization.Serializable
import org.oremif.cohere.api.types.ChatConnector
import org.oremif.cohere.api.types.CitationQuality
import org.oremif.cohere.api.types.Message
import org.oremif.cohere.api.types.PromptTruncation
import org.oremif.cohere.api.types.ResponseFormat
import org.oremif.cohere.api.types.Tool
import org.oremif.cohere.api.types.ToolResult

/**
 * @param message Text input for the model to respond to.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments.
 * @param stream Defaults to `false`.
 * When `true`, the response will be a JSON stream of events. The final event will contain the complete response, and will have an `event_type` of `"stream-end"`.
 * Streaming is beneficial for user interfaces that render the contents of the response piece by piece, as it gets generated.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 *
 * @param body see docs [ChatRequest]
 */
public fun ChatRequest(
    message: String, stream: Boolean? = null, body: ChatRequest.Builder.() -> Unit
): ChatRequest =
    ChatRequest.Builder(message, stream).apply(body).build()

/**
 * @property message Text input for the model to respond to.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments.
 * @property model Defaults to `command-r-plus`.
 * The name of a compatible [Cohere model](https://docs.cohere.com/docs/models) or the ID of a [fine-tuned](https://docs.cohere.com/docs/chat-fine-tuning) model.
 * Compatible Deployments: Cohere Platform, Private Deployments.
 * @property stream Defaults to `false`.
 * When `true`, the response will be a JSON stream of events. The final event will contain the complete response, and will have an `event_type` of `"stream-end"`.
 * Streaming is beneficial for user interfaces that render the contents of the response piece by piece, as it gets generated.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property preamble When specified, the default Cohere preamble will be replaced with the provided one.
 * Preambles are a part of the prompt used to adjust the model's overall behavior and conversation style, and use the `SYSTEM` role.
 * The `SYSTEM` role is also used for the contents of the optional `chat_history=` parameter. When used with the `chat_history=` parameter it adds content throughout a conversation.
 * Conversely, when used with the `preamble=` parameter it adds content at the start of the conversation only.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property chatHistory A list of previous messages between the user and the model, giving the model conversational context for responding to the user's `message`.
 * Each item represents a single message in the chat history, excluding the current user turn.
 * It has two properties: `role` and `message`. The `role` identifies the sender (`CHATBOT`, `SYSTEM`, or `USER`), while the message contains the text content.
 * The chat_history parameter should not be used for `SYSTEM` messages in most cases.
 * Instead, to add a `SYSTEM` role message at the beginning of a conversation, the preamble parameter should be used.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property conversationId An alternative to `chat_history`. Providing a `conversation_id` creates or resumes a persisted conversation
 * with the specified ID. The ID can be any non-empty string.
 * Compatible Deployments: Cohere Platform.
 * @property promptTruncation Defaults to `AUTO` when `connectors` are specified and `OFF` in all other cases.
 *
 * Dictates how the prompt will be constructed.
 *
 * With `prompt_truncation` set to "AUTO",
 * some elements from `chat_history` and `documents` will be dropped in an attempt to construct a prompt that fits within the model's context length limit.
 * During this process the order of the documents and chat history will be changed and ranked by relevance.
 *
 * With `prompt_truncation` set to "AUTO_PRESERVE_ORDER",
 * some elements from `chat_history` and `documents` will be dropped in an attempt to construct a prompt that fits within the model's context length limit.
 * During this process the order of the documents and chat history will be preserved as they are inputted into the API.
 *
 * With `prompt_truncation` set to "OFF", no elements will be dropped. If the sum of the inputs exceeds the model's context length limit, a `TooManyTokens` error will be returned.
 * Compatible Deployments: Cohere Platform Only AUTO_PRESERVE_ORDER: Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property connectors Accepts `{"id": "web-search"}`, and/or the `"id"` for a custom connector, if you've created one.
 *
 * When specified, the model's reply will be enriched with information found by querying each of the connectors (RAG).
 * Compatible Deployments: Cohere Platform
 * @property searchQueriesOnly Defaults to `false`.
 *
 * When `true`, the response will only contain a list of generated search queries,
 * but no search will take place, and no reply from the model to the user's `message` will be generated.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property documents A list of relevant documents that the model can cite to generate a more accurate reply.
 * Each document is a string-string dictionary.
 *
 * Example:
 * ```
 * [ { "title": "Tall penguins", "text": "Emperor penguins are the tallest." }, { "title": "Penguin habitats", "text": "Emperor penguins only live in Antarctica." }, ]
 * ```
 *
 * Keys and values from each document will be serialized to a string and passed to the model.
 * The resulting generation will include citations that reference some of these documents.
 *
 * Some suggested keys are "text", "author", and "date".
 * For better generation quality, it is recommended to keep the total word count of the strings in the dictionary to under 300 words.
 *
 * An `id` field (string) can be optionally supplied to identify the document in the citations.
 * This field will not be passed to the model.
 *
 * An `_excludes` field (array of strings) can be optionally supplied to omit some key-value pairs from being shown to the model.
 * The omitted fields will still show up in the citation object. The "_excludes" field will not be passed to the model.
 *
 * See ['Document Mode'](https://docs.cohere.com/docs/retrieval-augmented-generation-rag#document-mode) in the guide for more information.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property citationQuality Defaults to `"accurate"`.
 *
 * Dictates the approach taken to generating citations as part of the RAG flow by allowing the user to specify whether they want `accurate` results, `"fast"` results or no results.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property temperature `0 to 1`, Defaults to `0.3`.
 *
 * A non-negative float that tunes the degree of randomness in generation. Lower temperatures mean less random generations, and higher temperatures mean more random generations.
 *
 * Randomness can be further maximized by increasing the value of the `p` parameter.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property maxTokens The maximum number of tokens the model will generate as part of the response.
 * Note: Setting a low value may result in incomplete generations.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property maxInputTokens The maximum number of input tokens to send to the model.
 * If not specified, `max_input_tokens` is the model's context length limit minus a small buffer.
 *
 * Input will be truncated according to the `prompt_truncation` parameter.
 * Compatible Deployments: Cohere Platform
 * @property k `0 to 500`, Defaults to 0.
 * Ensures only the top `k` most likely tokens are considered for generation at each step.
 * Defaults to `0`, min value of `0`, max value of `500`.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property p `0.01 to 0.99`, Defaults to 0.75.
 * Ensures that only the most likely tokens, with total probability mass of p, are considered for generation at each step.
 * If both `k` and `p` are enabled, `p` acts after `k`.
 * Defaults to `0.75`. min value of `0.01`, max value of `0.99`.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property seed `0 to 18446744073709552000`.
 * If specified, the backend will make a best effort to sample tokens
 * deterministically, such that repeated requests with the same
 * seed and parameters should return the same result. However,
 * determinism cannot be totally guaranteed.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property stopSequences A list of up to 5 strings that the model will use to stop generation.
 * If the model generates a string that matches any of the strings in the list,
 * it will stop generating tokens and return the generated text up to that point, not including the stop sequence.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property frequencyPenalty Defaults to `0.0`, min value of `0.0`, max value of `1.0`.
 *
 * Used to reduce repetitiveness of generated tokens. The higher the value,
 * the stronger a penalty is applied to previously present tokens,
 * proportional to how many times they have already appeared in the prompt or prior generation.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property presencePenalty Defaults to `0.0`, min value of `0.0`, max value of `1.0`.
 *
 * Used to reduce repetitiveness of generated tokens.
 * Similar to frequency_penalty, except that this penalty is applied equally to all tokens that have already appeared,
 * regardless of their exact frequencies.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property tools A list of available tools (functions) that the model may suggest invoking before producing a text response.
 *
 * When `tools` is passed (without `tool_results`),
 * the `text` field in the response will be `""` and the `tool_calls` field in the response will be populated with a list of tool calls that need to be made.
 * If no calls need to be made, the `tool_calls` array will be empty.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property toolResults A list of results from invoking tools recommended by the model in the previous chat turn.
 * Results are used to produce a text response and will be referenced in citations. When using `tool_results`, `tools` must be passed as well.
 * Each tool_result contains information about how it was invoked, as well as a list of outputs in the form of dictionaries.
 *
 * **Note**: `outputs` must be a list of objects.
 * If your tool returns a single object (eg `{"status": 200}`), make sure to wrap it in a list.
 * ```
 * tool_results = [
 *   {
 *     "call": {
 *       "name": <tool name>,
 *       "parameters": {
 *         <param name>: <param value>
 *       }
 *     },
 *     "outputs": [{
 *       <key>: <value>
 *     }]
 *   },
 *   ...
 * ]
 * ```
 * **Note**: Chat calls with `tool_results` should not be included in the Chat history to avoid duplication of the message text.
 * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
 * @property forceSingleStep Forces the chat to be single step. Defaults to `false`.
 * @property responseFormat Configuration for forcing the model output to adhere to the specified format.
 * Supported on [Command R](https://docs.cohere.com/docs/command-r),
 * [Command R+](https://docs.cohere.com/docs/command-r-plus) and newer models.
 *
 * The model can be forced into outputting JSON objects (with up to 5 levels of nesting) by setting `{ "type": "json_object" }`.
 *
 * A [JSON Schema](https://json-schema.org/) can optionally be provided, to ensure a specific structure.
 *
 * **Note**: When using `{ "type": "json_object" }` your `message` should always explicitly instruct the model to generate a JSON (eg: "Generate a JSON ...").
 * Otherwise the model may end up getting stuck generating an infinite stream of characters and eventually run out of context length.
 * **Limitation**: The parameter is not supported in RAG mode (when any of `connectors`, `documents`, `tools`, `tool_results` are provided).
 */
@Serializable
public data class ChatRequest(
    val message: String,
    val model: String? = null,
    var stream: Boolean? = null,
    val preamble: String? = null,
    val chatHistory: List<Message>? = null,
    val conversationId: String? = null,
    val promptTruncation: PromptTruncation? = null,
    val connectors: List<ChatConnector>? = null,
    val searchQueriesOnly: Boolean? = null,
    val documents: List<Map<String, String>>? = null,
    val citationQuality: CitationQuality? = null,
    val temperature: Double? = null,
    val maxTokens: Int? = null,
    val maxInputTokens: Int? = null,
    val k: Int? = null,
    val p: Double? = null,
    val seed: Int? = null,
    val stopSequences: List<String>? = null,
    val frequencyPenalty: Double? = null,
    val presencePenalty: Double? = null,
    val tools: List<Tool>? = null,
    val toolResults: List<ToolResult>? = null,
    val forceSingleStep: Boolean? = null,
    val responseFormat: ResponseFormat? = null,
) {

    public class Builder internal constructor(private val message: String, private val stream: Boolean? = null) {
        private var model: String? = null
        private var preamble: String? = null
        private var chatHistory: List<Message>? = null
        private var conversationId: String? = null
        private var promptTruncation: PromptTruncation? = null
        private var connectors: List<ChatConnector>? = null
        private var searchQueriesOnly: Boolean? = null
        private var documents: List<Map<String, String>>? = null
        private var citationQuality: CitationQuality? = null
        private var temperature: Double? = null
        private var maxTokens: Int? = null
        private var maxInputTokens: Int? = null
        private var k: Int? = null
        private var p: Double? = null
        private var seed: Int? = null
        private var stopSequences: List<String>? = null
        private var frequencyPenalty: Double? = null
        private var presencePenalty: Double? = null
        private var tools: List<Tool>? = null
        private var toolResults: List<ToolResult>? = null
        private var forceSingleStep: Boolean? = null
        private var responseFormat: ResponseFormat? = null

        /**
         * Set property `model`. Defaults to `command-r-plus`.
         * The name of a compatible [Cohere model](https://docs.cohere.com/docs/models) or the ID of a [fine-tuned](https://docs.cohere.com/docs/chat-fine-tuning) model.
         * Compatible Deployments: Cohere Platform, Private Deployments.
         */
        public fun model(model: String): Unit = let { this.model = model }

        /**
         * Set property `preamble`. When specified, the default Cohere preamble will be replaced with the provided one.
         * Preambles are a part of the prompt used to adjust the model's overall behavior and conversation style, and use the `SYSTEM` role.
         * The `SYSTEM` role is also used for the contents of the optional `chat_history=` parameter. When used with the `chat_history=` parameter it adds content throughout a conversation.
         * Conversely, when used with the `preamble=` parameter it adds content at the start of the conversation only.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun preamble(preamble: String): Unit = let { this.preamble = preamble }

        /**
         * Set property `chatHistory`. A list of previous messages between the user and the model, giving the model conversational context for responding to the user's `message`.
         * Each item represents a single message in the chat history, excluding the current user turn.
         * It has two properties: `role` and `message`. The `role` identifies the sender (`CHATBOT`, `SYSTEM`, or `USER`), while the message contains the text content.
         * The chat_history parameter should not be used for `SYSTEM` messages in most cases.
         * Instead, to add a `SYSTEM` role message at the beginning of a conversation, the preamble parameter should be used.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun chatHistory(chatHistory: List<Message>): Unit = let { this.chatHistory = chatHistory }

        /**
         * Set property `conversationId`. An alternative to `chat_history`. Providing a `conversation_id` creates or resumes a persisted conversation
         * with the specified ID. The ID can be any non-empty string.
         * Compatible Deployments: Cohere Platform.
         */
        public fun conversationId(conversationId: String): Unit = let { this.conversationId = conversationId }

        /**
         * Set property `promptTruncation`. Defaults to `AUTO` when `connectors` are specified and `OFF` in all other cases.
         *
         * Dictates how the prompt will be constructed.
         *
         * With `prompt_truncation` set to "AUTO",
         * some elements from `chat_history` and `documents` will be dropped in an attempt to construct a prompt that fits within the model's context length limit.
         * During this process the order of the documents and chat history will be changed and ranked by relevance.
         *
         * With `prompt_truncation` set to "AUTO_PRESERVE_ORDER",
         * some elements from `chat_history` and `documents` will be dropped in an attempt to construct a prompt that fits within the model's context length limit.
         * During this process the order of the documents and chat history will be preserved as they are inputted into the API.
         *
         * With `prompt_truncation` set to "OFF", no elements will be dropped. If the sum of the inputs exceeds the model's context length limit, a `TooManyTokens` error will be returned.
         * Compatible Deployments: Cohere Platform Only AUTO_PRESERVE_ORDER: Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun promptTruncation(promptTruncation: PromptTruncation): Unit =
            let { this.promptTruncation = promptTruncation }

        /**
         * Set property `connectors`. Accepts `{"id": "web-search"}`, and/or the `"id"` for a custom connector, if you've created one.
         *
         * When specified, the model's reply will be enriched with information found by querying each of the connectors (RAG).
         * Compatible Deployments: Cohere Platform
         */
        public fun connectors(connectors: List<ChatConnector>): Unit = let { this.connectors = connectors }

        /**
         * Set property `searchQueriesOnly`. Defaults to `false`.
         *
         * When `true`, the response will only contain a list of generated search queries,
         * but no search will take place, and no reply from the model to the user's `message` will be generated.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun searchQueriesOnly(searchQueriesOnly: Boolean): Unit =
            let { this.searchQueriesOnly = searchQueriesOnly }

        /**
         * Set property `documents`. A list of relevant documents that the model can cite to generate a more accurate reply.
         * Each document is a string-string dictionary.
         *
         * Example:
         * ```
         * [ { "title": "Tall penguins", "text": "Emperor penguins are the tallest." }, { "title": "Penguin habitats", "text": "Emperor penguins only live in Antarctica." }, ]
         * ```
         *
         * Keys and values from each document will be serialized to a string and passed to the model.
         * The resulting generation will include citations that reference some of these documents.
         *
         * Some suggested keys are "text", "author", and "date".
         * For better generation quality, it is recommended to keep the total word count of the strings in the dictionary to under 300 words.
         *
         * An `id` field (string) can be optionally supplied to identify the document in the citations.
         * This field will not be passed to the model.
         *
         * An `_excludes` field (array of strings) can be optionally supplied to omit some key-value pairs from being shown to the model.
         * The omitted fields will still show up in the citation object. The "_excludes" field will not be passed to the model.
         *
         * See ['Document Mode'](https://docs.cohere.com/docs/retrieval-augmented-generation-rag#document-mode) in the guide for more information.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun documents(documents: List<Map<String, String>>): Unit = let { this.documents = documents }

        /**
         * Set property `citationQuality`. Defaults to `"accurate"`.
         *
         * Dictates the approach taken to generating citations as part of the RAG flow by allowing the user to specify whether they want `accurate` results, `"fast"` results or no results.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun citationQuality(citationQuality: CitationQuality): Unit =
            let { this.citationQuality = citationQuality }

        /**
         * Set property `temperature`. `0 to 1`, Defaults to `0.3`.
         *
         * A non-negative float that tunes the degree of randomness in generation. Lower temperatures mean less random generations, and higher temperatures mean more random generations.
         *
         * Randomness can be further maximized by increasing the value of the `p` parameter.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun temperature(temperature: Double): Unit = let { this.temperature = temperature }

        /**
         * Set property `maxTokens`. The maximum number of tokens the model will generate as part of the response.
         * Note: Setting a low value may result in incomplete generations.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun maxTokens(maxTokens: Int): Unit = let { this.maxTokens = maxTokens }

        /**
         * Set property `maxInputTokens`. The maximum number of input tokens to send to the model.
         * If not specified, `max_input_tokens` is the model's context length limit minus a small buffer.
         *
         * Input will be truncated according to the `prompt_truncation` parameter.
         * Compatible Deployments: Cohere Platform
         */
        public fun maxInputTokens(maxInputTokens: Int): Unit = let { this.maxInputTokens = maxInputTokens }

        /**
         * Set property `k`. `0 to 500`, Defaults to 0.
         * Ensures only the top `k` most likely tokens are considered for generation at each step.
         * Defaults to `0`, min value of `0`, max value of `500`.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun k(k: Int): Unit = let { this.k = k }

        /**
         * Set property `p`. `0.01 to 0.99`, Defaults to 0.75.
         * Ensures that only the most likely tokens, with total probability mass of p, are considered for generation at each step.
         * If both `k` and `p` are enabled, `p` acts after `k`.
         * Defaults to `0.75`. min value of `0.01`, max value of `0.99`.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun p(p: Double): Unit = let { this.p = p }

        /**
         * Set property `seed`. `0 to 18446744073709552000`.
         * If specified, the backend will make a best effort to sample tokens
         * deterministically, such that repeated requests with the same
         * seed and parameters should return the same result. However,
         * determinism cannot be totally guaranteed.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun seed(seed: Int): Unit = let { this.seed = seed }

        /**
         * Set property `stopSequences`. A list of up to 5 strings that the model will use to stop generation.
         * If the model generates a string that matches any of the strings in the list,
         * it will stop generating tokens and return the generated text up to that point, not including the stop sequence.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun stopSequences(stopSequences: List<String>): Unit = let { this.stopSequences = stopSequences }

        /**
         * Set property `frequencyPenalty`. Defaults to `0.0`, min value of `0.0`, max value of `1.0`.
         *
         * Used to reduce repetitiveness of generated tokens. The higher the value,
         * the stronger a penalty is applied to previously present tokens,
         * proportional to how many times they have already appeared in the prompt or prior generation.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun frequencyPenalty(frequencyPenalty: Double): Unit = let { this.frequencyPenalty = frequencyPenalty }

        /**
         * Set property `presencePenalty`. Defaults to `0.0`, min value of `0.0`, max value of `1.0`.
         *
         * Used to reduce repetitiveness of generated tokens.
         * Similar to frequency_penalty, except that this penalty is applied equally to all tokens that have already appeared,
         * regardless of their exact frequencies.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun presencePenalty(presencePenalty: Double): Unit = let { this.presencePenalty = presencePenalty }

        /**
         * Set property `tools`. A list of available tools (functions) that the model may suggest invoking before producing a text response.
         *
         * When `tools` is passed (without `tool_results`),
         * the `text` field in the response will be `""` and the `tool_calls` field in the response will be populated with a list of tool calls that need to be made.
         * If no calls need to be made, the `tool_calls` array will be empty.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun tools(tools: List<Tool>): Unit = let { this.tools = tools }

        /**
         * Set property `toolResults`. A list of results from invoking tools recommended by the model in the previous chat turn.
         * Results are used to produce a text response and will be referenced in citations. When using `tool_results`, `tools` must be passed as well.
         * Each tool_result contains information about how it was invoked, as well as a list of outputs in the form of dictionaries.
         *
         * **Note**: `outputs` must be a list of objects.
         * If your tool returns a single object (eg `{"status": 200}`), make sure to wrap it in a list.
         * ```
         * tool_results = [
         *   {
         *     "call": {
         *       "name": <tool name>,
         *       "parameters": {
         *         <param name>: <param value>
         *       }
         *     },
         *     "outputs": [{
         *       <key>: <value>
         *     }]
         *   },
         *   ...
         * ]
         * ```
         * **Note**: Chat calls with `tool_results` should not be included in the Chat history to avoid duplication of the message text.
         * Compatible Deployments: Cohere Platform, Azure, AWS Sagemaker/Bedrock, Private Deployments
         */
        public fun toolResults(toolResults: List<ToolResult>): Unit = let { this.toolResults = toolResults }

        /**
         * Set property `forceSingleStep`. Forces the chat to be single step. Defaults to `false`.
         */
        public fun forceSingleStep(forceSingleStep: Boolean): Unit = let { this.forceSingleStep = forceSingleStep }

        /**
         * Set property `responseFormat`. Configuration for forcing the model output to adhere to the specified format.
         * Supported on [Command R](https://docs.cohere.com/docs/command-r),
         * [Command R+](https://docs.cohere.com/docs/command-r-plus) and newer models.
         *
         * The model can be forced into outputting JSON objects (with up to 5 levels of nesting) by setting `{ "type": "json_object" }`.
         *
         * A [JSON Schema](https://json-schema.org/) can optionally be provided, to ensure a specific structure.
         *
         * **Note**: When using `{ "type": "json_object" }` your `message` should always explicitly instruct the model to generate a JSON (eg: "Generate a JSON ...").
         * Otherwise the model may end up getting stuck generating an infinite stream of characters and eventually run out of context length.
         * **Limitation**: The parameter is not supported in RAG mode (when any of `connectors`, `documents`, `tools`, `tool_results` are provided).
         */
        public fun responseFormat(responseFormat: ResponseFormat): Unit = let { this.responseFormat = responseFormat }

        public fun build(): ChatRequest {
            return ChatRequest(
                message = message,
                model = model,
                stream = stream,
                preamble = preamble,
                chatHistory = chatHistory,
                conversationId = conversationId,
                promptTruncation = promptTruncation,
                connectors = connectors,
                searchQueriesOnly = searchQueriesOnly,
                documents = documents,
                citationQuality = citationQuality,
                temperature = temperature,
                maxTokens = maxTokens,
                maxInputTokens = maxInputTokens,
                k = k,
                p = p,
                seed = seed,
                stopSequences = stopSequences,
                frequencyPenalty = frequencyPenalty,
                presencePenalty = presencePenalty,
                tools = tools,
                toolResults = toolResults,
                forceSingleStep = forceSingleStep,
                responseFormat = responseFormat,
            )
        }
    }
}