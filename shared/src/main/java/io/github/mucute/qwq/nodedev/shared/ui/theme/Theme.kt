package io.github.mucute.qwq.nodedev.shared.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.mucute.qwq.nodedev.shared.ui.icons.NestEcoLeaf
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController

inline val NodeAppIconBackgroundColor: Color
    get() = Color(0xFF3E5FCD)

inline val NodeAppIconForegroundColor: Color
    get() = Color.White

inline val NodeAppIcon: ImageVector
    get() = Icons.Rounded.NestEcoLeaf

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