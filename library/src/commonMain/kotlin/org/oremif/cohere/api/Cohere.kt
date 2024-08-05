package org.oremif.cohere.api

import io.ktor.client.*
import io.ktor.client.plugins.auth.*

public class Cohere internal constructor() {

    public val client: HttpClient = HttpClient() {
        install(Auth) {

        }
    }
}