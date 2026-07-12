package io.github.mucute.qwq.nodedev.page.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.fillSize
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImportExport
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.component.CardGroup
import io.github.mucute.qwq.nodedev.shared.ui.component.CardItem
import io.github.mucute.qwq.nodedev.shared.ui.icons.Package2Outline
import io.github.mucute.qwq.nodedev.shared.ui.icons.Syringe
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun ExtensionsPage() {
    Column(
        Modifier
            .styleable {
                fillSize()
                contentPadding(16.dp)
            },
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardItem(
            leadingIcon = Icons.Rounded.Syringe,
            trailingIcon = Icons.Rounded.ImportExport,
            cornerRadius = CardDefaults.CornerRadius,
            pressFeedbackType = PressFeedbackType.Sink,
            showIndication = true,
            colors = CardDefaults.defaultColors(
                color = MiuixTheme.colorScheme.primary,
                contentColor = MiuixTheme.colorScheme.onPrimary
            ),
            title = stringResource(R.string.import_extension),
            summary = stringResource(R.string.import_extension_summary)
        ) {

        }

        CardGroup(
            title = stringResource(R.string.installed_plugin_list)
        ) {
            LazyColumn(
                Modifier
                    .fillMaxWidth(),
            ) {
                items(1) {
                    ExtensionCard()
                }
            }
        }

        CardGroup(
            title = stringResource(R.string.plugin_list)
        ) {
            LazyColumn(
                Modifier
                    .fillMaxWidth(),
            ) {
                items(20) {
                    ExtensionCard()
                }
            }
        }
    }
}

@Composable
private fun ExtensionCard(

) {
    CardItem(
        leadingIcon = Icons.Rounded.Package2Outline,
        cornerRadius = 0.dp,
        pressFeedbackType = PressFeedbackType.None,
        showIndication = true,
        title = stringResource(R.string.new_project),
        summary = stringResource(R.string.new_project_summary),
        onClick = {},
    )
}