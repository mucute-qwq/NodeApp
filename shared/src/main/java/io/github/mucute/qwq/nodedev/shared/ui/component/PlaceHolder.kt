package io.github.mucute.qwq.nodedev.shared.ui.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
inline fun Placeholder(
    empty: Boolean,
    title: String,
    summary: String? = null,
    crossinline content: @Composable () -> Unit
) {
    Crossfade(
        targetState = empty
    ) {
        if (it) {
            Column(
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                summary?.let { summary ->
                    Text(title, style = MiuixTheme.textStyles.title3, fontWeight = FontWeight.Bold)
                    SmallTitle(summary)
                }
            }
        } else {
            content()
        }
    }
}