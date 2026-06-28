package io.github.mucute.qwq.nodedev.page.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.component.ActionCardItem
import io.github.mucute.qwq.nodedev.shared.ui.component.CardGroup
import io.github.mucute.qwq.nodedev.shared.ui.component.CardItem
import io.github.mucute.qwq.nodedev.shared.ui.icons.Package2
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.menu.WindowIconDropdownMenu
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@OptIn(ExperimentalFlexBoxApi::class, ExperimentalFoundationStyleApi::class)
@Composable
fun HomePage() {
    Column(
        Modifier
            .fillMaxSize()
            .styleable {
                contentPadding(16.dp)
            },
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardItem(
            leadingIcon = Icons.Rounded.Star,
            trailingIcon = Icons.AutoMirrored.Rounded.ArrowForward,
            cornerRadius = CardDefaults.CornerRadius,
            pressFeedbackType = PressFeedbackType.Sink,
            showIndication = true,
            colors = CardDefaults.defaultColors(
                color = MiuixTheme.colorScheme.primary,
                contentColor = MiuixTheme.colorScheme.onPrimary
            ),
            title = stringResource(R.string.new_project),
            summary = stringResource(R.string.new_project_summary)
        ) {

        }

        CardGroup(
            stringResource(R.string.project_list)
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize(),
            ) {
                items(20) {
                    ProjectCard {

                    }
                }
            }
        }

    }
}

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
private fun ProjectCard(
    onEnterProject: () -> Unit
) {
    val resources = LocalResources.current
    val dropdownEntry = remember(resources) {
        DropdownEntry(
            listOf(
                DropdownItem(resources.getString(R.string.rename), icon = {
                    Icon(Icons.Rounded.Edit, null, modifier = it.size(20.dp))
                }),
                DropdownItem(resources.getString(R.string.delete), icon = {
                    Icon(Icons.Rounded.Delete, null, modifier = it.size(20.dp))
                }),
            )
        )
    }
    ActionCardItem(
        entry = dropdownEntry,
        leadingIcon = Icons.Rounded.Package2,
        trailingIcon = Icons.Rounded.MoreVert,
        title = "NodeDev",
        summary = "com.example.app"
    ) {

    }
}