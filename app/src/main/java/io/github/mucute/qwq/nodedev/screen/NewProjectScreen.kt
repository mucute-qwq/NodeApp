package io.github.mucute.qwq.nodedev.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.mucute.qwq.nodedev.model.ProjectTemplate
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.composition.local.LocalBackStack
import io.github.mucute.qwq.nodedev.shared.composition.local.LocalSnackBarState
import io.github.mucute.qwq.nodedev.shared.navigation.NavScreen
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppIntent
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppViewModel
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.DropdownDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.popup.WindowDropdownPopup

@Composable
fun NewProjectScreen() {
    val backStack = LocalBackStack.current
    val scrollBehavior = MiuixScrollBehavior()
    val snackBarHostState = retain { SnackbarHostState() }

    CompositionLocalProvider(LocalSnackBarState provides snackBarHostState) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = stringResource(R.string.new_project),
                    subtitle = stringResource(R.string.new_project_summary),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                backStack -= NavScreen.NewProject
                            }
                        ) {
                            Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(snackBarHostState)
            },
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                NewProjectScreenContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewProjectScreenContent() {
    val coroutineScope = rememberCoroutineScope()
    val nodeAppViewModel: NodeAppViewModel = viewModel()
    val nodeAppIntent = nodeAppViewModel.intent
    var projectName by retain { mutableStateOf("MyApp") }
    var packageName by retain { mutableStateOf("com.myapp.instance") }
    var selectedProjectTemplateIndex by retain { mutableIntStateOf(0) }
    val projectTemplateItems = DropdownEntry(
        items = ProjectTemplate.entries.map {
            DropdownItem(
                text = stringResource(it.templateResId),
                onClick = { selectedProjectTemplateIndex = it.ordinal },
                selected = it.ordinal == selectedProjectTemplateIndex
            )
        }
    )
    var isProjectTemplateExpanded by retain { mutableStateOf(false) }
    val rotate by animateFloatAsState(
        targetValue = if (isProjectTemplateExpanded) 180f else 0f
    )

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = projectName,
            onValueChange = {
                projectName = it
            },
            label = stringResource(R.string.project_name),
            useLabelAsPlaceholder = false,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = packageName,
            onValueChange = {
                packageName = it
            },
            label = stringResource(R.string.package_name),
            useLabelAsPlaceholder = false,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        ExposedDropdownMenuBox(
            expanded = isProjectTemplateExpanded,
            onExpandedChange = {
                isProjectTemplateExpanded = it
            },
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused) {
                        isProjectTemplateExpanded = true
                    }
                }
        ) {
            TextField(
                value = stringResource(ProjectTemplate.entries[selectedProjectTemplateIndex].templateResId),
                onValueChange = {},
                label = stringResource(R.string.project_template),
                useLabelAsPlaceholder = false,
                readOnly = true,
                trailingIcon = {
                    Icon(
                        Icons.Rounded.ArrowDropDown,
                        null,
                        Modifier
                            .padding(horizontal = 8.dp)
                            .rotate(rotate)
                    )
                },
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )
            WindowDropdownPopup(
                entry = projectTemplateItems,
                show = isProjectTemplateExpanded,
                onDismiss = {
                    isProjectTemplateExpanded = false
                },
                onDismissFinished = {},
                maxHeight = null,
                dropdownColors = DropdownDefaults.dropdownColors()
            )
        }

        TextButton(
            text = stringResource(R.string.create),
            colors = ButtonDefaults.textButtonColorsPrimary(),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                coroutineScope.launch {
                    nodeAppIntent.send(
                        NodeAppIntent.NewProject(
                            projectName, packageName,
                            ProjectTemplate.entries[selectedProjectTemplateIndex]
                        )
                    )
                }
            }
        )
    }
}