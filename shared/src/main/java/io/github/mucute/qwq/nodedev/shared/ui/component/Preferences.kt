package io.github.mucute.qwq.nodedev.shared.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.fillWidth
import androidx.compose.foundation.style.styleable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun PreferenceGroup(
    text: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        SmallTitle(text)
        Card(
            Modifier
                .styleable {
                    contentPadding(horizontal = 16.dp, vertical = 0.dp)
                    fillWidth()
                },
            content = content
        )
    }
}