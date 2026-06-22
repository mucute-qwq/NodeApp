package io.github.mucute.qwq.nodedev.composition.local

import androidx.compose.runtime.staticCompositionLocalOf
import top.yukonga.miuix.kmp.basic.SnackbarHostState

val LocalSnackBarState =
    staticCompositionLocalOf<SnackbarHostState> { noLocalProvidedFor("LocalSnackBarState") }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}