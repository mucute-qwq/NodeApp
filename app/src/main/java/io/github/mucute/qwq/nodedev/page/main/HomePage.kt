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
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.PushPin
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.mucute.qwq.nodedev.model.Project
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.composition.local.LocalBackStack
import io.github.mucute.qwq.nodedev.navigation.NavScreen
import io.github.mucute.qwq.nodedev.shared.ui.component.ActionCardItem
import io.github.mucute.qwq.nodedev.shared.ui.component.CardGroup
import io.github.mucute.qwq.nodedev.shared.ui.component.CardItem
import io.github.mucute.qwq.nodedev.shared.ui.component.Placeholder
import io.github.mucute.qwq.nodedev.shared.ui.icons.Package2Outline
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppIntent
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@OptIn(ExperimentalFlexBoxApi::class, ExperimentalFoundationStyleApi::class)
@Composable
fun HomePage() {
    val coroutineScope = rememberCoroutineScope()
    val backStack = LocalBackStack.current
    val nodeAppViewModel: NodeAppViewModel = viewModel()
    val nodeAppState by nodeAppViewModel.state.collectAsStateWithLifecycle()
    val nodeAppIntent = nodeAppViewModel.intent
    val projects = nodeAppState.projects
    val pinnedProjects by produceState(emptyList(), projects) {
        value = withContext(Dispatchers.IO) { projects.filter { it.pinned } }
    }
    val unpinnedProjects by produceState(emptyList(), projects) {
        value = withContext(Dispatchers.IO) { projects.filter { !it.pinned } }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        coroutineScope.launch {
            nodeAppIntent.send(NodeAppIntent.RefreshProjects)
        }
    }

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

        if (projects.isNotEmpty()) {
            CardGroup(
                title = stringResource(R.string.pinned_project_list)
            ) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth(),
                ) {
                    items(pinnedProjects.size) {
                        val pinnedProject = pinnedProjects[it]
                        ProjectCard(pinnedProject)
                    }
                }
            }
        }

        Placeholder(
            empty = projects.isEmpty(),
            title = stringResource(R.string.no_projects_yet),
            summary = stringResource(R.string.no_projects_yet_summary)
        ) {
            CardGroup(
                stringResource(R.string.project_list)
            ) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth(),
                ) {
                    items(unpinnedProjects.size) {
                        val unpinnedProject = unpinnedProjects[it]
                        ProjectCard(unpinnedProject)
                    }
                }
            }
        }

    }
}

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
private fun ProjectCard(
    project: Project
) {
    val coroutineScope = rememberCoroutineScope()
    val backStack = LocalBackStack.current
    val nodeAppViewModel: NodeAppViewModel = viewModel()
    val nodeAppIntent = nodeAppViewModel.intent
    val dropdownEntry = DropdownEntry(
        listOf(
            DropdownItem(
                text = stringResource(if (project.pinned) R.string.unpin else R.string.pin),
                icon = {
                    Icon(Icons.Rounded.PushPin, null, modifier = it.size(20.dp))
                },
                onClick = {
                    coroutineScope.launch {
                        nodeAppIntent.send(NodeAppIntent.PinOrUnpinProject(project))
                    }
                }
            ),
            DropdownItem(
                text = stringResource(R.string.rename),
                icon = {
                    Icon(Icons.Rounded.Edit, null, modifier = it.size(20.dp))
                },
                onClick = {
                    coroutineScope.launch {
                        nodeAppIntent.send(NodeAppIntent.RenameProject(project))
                    }
                }
            ),
            DropdownItem(
                text = stringResource(R.string.delete),
                icon = {
                    Icon(Icons.Rounded.Delete, null, modifier = it.size(20.dp))
                },
                onClick = {
                    coroutineScope.launch {
                        nodeAppIntent.send(NodeAppIntent.DeleteProject(project))
                    }
                }
            ),
        )
    )

    ActionCardItem(
        entry = dropdownEntry,
        leadingIcon = Icons.Rounded.Package2Outline,
        trailingIcon = Icons.Rounded.MoreVert,
        title = project.name,
        summary = project.packageName
    ) {
        backStack += NavScreen.Workspace(project)
    }
}