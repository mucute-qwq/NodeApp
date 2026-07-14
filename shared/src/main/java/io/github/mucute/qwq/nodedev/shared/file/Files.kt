package io.github.mucute.qwq.nodedev.shared.file

import io.github.mucute.qwq.nodedev.shared.application.AppContext
import java.io.File

val ProjectFolder by lazy { File(AppContext.instance.filesDir, "projects") }

internal fun initializeFiles() {
    ProjectFolder.mkdirs()
}