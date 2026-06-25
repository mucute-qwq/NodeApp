package io.github.mucute.qwq.nodedev.shared.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController

@Composable
fun NodeDevTheme(
    content: @Composable () -> Unit
) {
    val themeController = retain { ThemeController(ColorSchemeMode.System) }
    MiuixTheme(
        controller = themeController,
        content = content
    )
}