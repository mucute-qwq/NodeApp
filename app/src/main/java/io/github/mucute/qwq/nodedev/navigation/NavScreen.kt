package io.github.mucute.qwq.nodedev.navigation

import androidx.compose.runtime.Immutable
import androidx.navigation3.runtime.NavKey
import io.github.mucute.qwq.nodedev.model.Project

@Immutable
sealed interface NavScreen : NavKey {

    data object Guide : NavScreen

    data object Main : NavScreen

    data object NewProject : NavScreen

    data class Workspace(val project: Project) : NavScreen

}