package io.github.mucute.qwq.nodedev.shared.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.retain.retain
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController

@Composable
fun NodeAppTheme(
    controller: ThemeController = retain { ThemeController(ColorSchemeMode.System) },
    content: @Composable () -> Unit
) {
    MiuixTheme(
        controller = controller,
        content = content
    )
}