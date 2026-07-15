package io.github.mucute.qwq.nodedev.model

import androidx.compose.runtime.Immutable
import io.github.mucute.qwq.nodedev.shared.file.ProjectFolder
import kotlinx.serialization.Serializable
import java.io.File

@Immutable
@Serializable
data class Project(
    val name: String,
    val packageName: String,
    val openedFiles: List<String>,
    val pinned: Boolean
) {

    inline val rootProjectFolder: File
        get() = ProjectFolder.resolve(name)

    inline val workspaceFolder: File
        get() = rootProjectFolder.resolve(".workspace")

    inline val projectFile: File
        get() = workspaceFolder.resolve("project.json")

}