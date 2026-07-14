package io.github.mucute.qwq.nodedev.shared.util

import kotlinx.serialization.json.Json

val JsonFormatted = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
}

val JsonDefault = Json {
    ignoreUnknownKeys = true
}