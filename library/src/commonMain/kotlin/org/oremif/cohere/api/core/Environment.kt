package org.oremif.cohere.api.core

public class Environment(public val url: String) {
    public companion object {
        public val PRODUCTION: Environment =
            Environment("https://api.cohere.com/v1")

        public fun custom(url: String): Environment =
            Environment(url)
    }
}