package io.github.mucute.qwq.nodedev.page.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.rounded.ArrowForward
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
        NewProjectCard {

        }

        Column {
            SmallTitle(stringResource(R.string.project_list))

            Card {
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
}

@Composable
private fun NewProjectCard(
    onNewProject: () -> Unit
) {
    Card(
        onClick = onNewProject,
        insideMargin = PaddingValues(16.dp),
        pressFeedbackType = PressFeedbackType.Sink,
        colors = CardDefaults.defaultColors(
            color = MiuixTheme.colorScheme.primary,
            contentColor = MiuixTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(Icons.Rounded.Star, null)
            Column(Modifier.weight(1f)) {
                Text(
                    stringResource(R.string.new_project),
                    style = MiuixTheme.textStyles.title3,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    stringResource(R.string.new_project_content),
                    style = MiuixTheme.textStyles.body2
                )
            }
            Box(
                Modifier
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.ArrowForward, null)
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
    Card(
        onClick = onEnterProject,
        insideMargin = PaddingValues(16.dp),
        cornerRadius = 0.dp,
        showIndication = true,
        pressFeedbackType = PressFeedbackType.None,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(Icons.Rounded.Package2, null)
            Column(Modifier.weight(1f)) {
                Text(
                    "NodeDev",
                    style = MiuixTheme.textStyles.title3,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "com.example.app",
                    style = MiuixTheme.textStyles.body2
                )
            }

            WindowIconDropdownMenu(
                entry = dropdownEntry
            ) {
                Icon(Icons.Rounded.MoreVert, null)
            }
        }
    }
}