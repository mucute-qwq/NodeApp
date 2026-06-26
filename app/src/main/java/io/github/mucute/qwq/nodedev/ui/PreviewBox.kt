package io.github.mucute.qwq.nodedev.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.mucute.qwq.nodedev.shared.ui.theme.NodeAppTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun PreviewBox(content: @Composable BoxScope.() -> Unit) {
    NodeAppTheme {
        Box(Modifier.background(MiuixTheme.colorScheme.surface), content = content)
    }
}