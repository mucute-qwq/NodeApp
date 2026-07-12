package io.github.mucute.qwq.nodedev.page.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
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
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.PushPin
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.composition.local.LocalBackStack
import io.github.mucute.qwq.nodedev.shared.navigation.NavScreen
import io.github.mucute.qwq.nodedev.shared.ui.component.ActionCardItem
import io.github.mucute.qwq.nodedev.shared.ui.component.CardGroup
import io.github.mucute.qwq.nodedev.shared.ui.component.CardItem
import io.github.mucute.qwq.nodedev.shared.ui.icons.Package2Outline
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@OptIn(ExperimentalFlexBoxApi::class, ExperimentalFoundationStyleApi::class)
@Composable
fun HomePage() {
    val backStack = LocalBackStack.current
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
            backStack += NavScreen.NewProject
        }

        CardGroup(
            title = stringResource(R.string.pinned_project_list)
        ) {
            LazyColumn(
                Modifier
                    .fillMaxWidth(),
            ) {
                items(1) {
                    ProjectCard {

                    }
                }
            }
        }

        CardGroup(
            stringResource(R.string.project_list)
        ) {
            LazyColumn(
                Modifier
                    .fillMaxWidth(),
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
                DropdownItem(resources.getString(R.string.pin), icon = {
                    Icon(Icons.Rounded.PushPin, null, modifier = it.size(20.dp))
                }),
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
        leadingIcon = Icons.Rounded.Package2Outline,
        trailingIcon = Icons.Rounded.MoreVert,
        title = "NodeDev",
        summary = "com.example.app"
    ) {

    }
}