package io.github.mucute.qwq.nodedev.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Project(
    val name: String,
    val packageName: String,
    val openedFiles: List<String>,
    val pinned: Boolean
)