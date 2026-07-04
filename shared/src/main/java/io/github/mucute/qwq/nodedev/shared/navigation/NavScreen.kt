package io.github.mucute.qwq.nodedev.shared.navigation

import androidx.compose.runtime.Immutable
import androidx.navigation3.runtime.NavKey

@Immutable
sealed interface NavScreen : NavKey {

    data object Guide : NavScreen

    data object Main : NavScreen

}