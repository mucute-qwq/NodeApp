package io.github.mucute.qwq.nodedev.shared.composition.local

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.NavKey
import top.yukonga.miuix.kmp.basic.SnackbarHostState

val LocalSnackBarState =
    staticCompositionLocalOf<SnackbarHostState> { noLocalProvidedFor("LocalSnackBarState") }

val LocalBackStack =
    staticCompositionLocalOf<SnapshotStateList<NavKey>> { noLocalProvidedFor("LocalBackStack") }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}