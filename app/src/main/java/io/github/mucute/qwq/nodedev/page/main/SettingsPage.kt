package io.github.mucute.qwq.nodedev.page.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrightnessAuto
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.component.PreferenceGroup
import io.github.mucute.qwq.nodedev.viewmodel.AppThemeMode
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppIntent
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppViewModel
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.preference.WindowSpinnerPreference

@Composable
fun SettingsPage() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ThemePreferenceGroup()
        GeneralPreferenceGroup()
        BuildAndRunPreferenceGroup()
        ExperimentalFeaturesGroup()
        DeveloperOptionsGroup()
        MorePreferenceGroup()
    }
}

@Composable
private fun ThemePreferenceGroup() {
    val coroutineScope = rememberCoroutineScope()
    val resources = LocalResources.current
    val appThemeDropdownItems = remember(resources) {
        listOf(
            DropdownItem(resources.getString(R.string.follow_system), icon = {
                Icon(Icons.Rounded.BrightnessAuto, null, modifier = it.size(20.dp))
            }),
            DropdownItem(resources.getString(R.string.light), icon = {
                Icon(Icons.Rounded.LightMode, null, modifier = it.size(20.dp))
            }),
            DropdownItem(resources.getString(R.string.dark), icon = {
                Icon(Icons.Rounded.DarkMode, null, modifier = it.size(20.dp))
            }),
        )
    }
    val codeEditorDropdownItems = remember(resources) {
        listOf(
            DropdownItem(resources.getString(R.string.follow_system), icon = {
                Icon(Icons.Rounded.BrightnessAuto, null, modifier = it.size(20.dp))
            }),
        )
    }

    val nodeAppViewModel: NodeAppViewModel = viewModel()
    val nodeAppState by nodeAppViewModel.state.collectAsStateWithLifecycle()

    PreferenceGroup(
        text = stringResource(R.string.theme)
    ) {
        WindowSpinnerPreference(
            title = stringResource(R.string.app_theme),
            summary = stringResource(R.string.app_theme_summary),
            items = appThemeDropdownItems,
            selectedIndex = nodeAppState.appThemeMode.ordinal,
            onSelectedIndexChange = {
                coroutineScope.launch {
                    nodeAppViewModel.intent.send(
                        NodeAppIntent.ChangeAppThemeMode(
                            AppThemeMode.entries[it],
                            nodeAppState.appThemeMonetColor
                        )
                    )
                }
            },
        )
        WindowSpinnerPreference(
            title = stringResource(R.string.code_editor_theme),
            summary = stringResource(R.string.code_editor_theme_summary),
            items = codeEditorDropdownItems,
            selectedIndex = 0,
            onSelectedIndexChange = {},
        )
        SwitchPreference(
            title = stringResource(R.string.monet_color),
            summary = stringResource(R.string.monet_color_summary),
            checked = nodeAppState.appThemeMonetColor,
            onCheckedChange = {
                coroutineScope.launch {
                    nodeAppViewModel.intent.send(
                        NodeAppIntent.ChangeAppThemeMode(
                            nodeAppState.appThemeMode,
                            !nodeAppState.appThemeMonetColor
                        )
                    )
                }
            }
        )
    }
}

@Composable
private fun GeneralPreferenceGroup() {
    val resources = LocalResources.current
    val codeEditorFontDropdownItems = remember {
        listOf(
            DropdownItem("JetBrains Mono")
        )
    }
    val languageDropdownItems = remember(resources) {
        listOf(
            DropdownItem(resources.getString(R.string.language_en)),
            DropdownItem(resources.getString(R.string.language_zh)),
        )
    }

    PreferenceGroup(
        text = stringResource(R.string.general)
    ) {
        WindowSpinnerPreference(
            title = stringResource(R.string.language),
            summary = stringResource(R.string.language_summary),
            items = languageDropdownItems,
            selectedIndex = 0,
            onSelectedIndexChange = {},
        )
        WindowSpinnerPreference(
            title = stringResource(R.string.code_editor_font),
            summary = stringResource(R.string.code_editor_theme_summary),
            items = codeEditorFontDropdownItems,
            selectedIndex = 0,
            onSelectedIndexChange = {},
        )
        SwitchPreference(
            title = stringResource(R.string.syntax_highlight),
            summary = stringResource(R.string.syntax_highlight_summary),
            checked = false,
            onCheckedChange = {

            }
        )
        SwitchPreference(
            title = stringResource(R.string.color_preview),
            summary = stringResource(R.string.color_preview_summary),
            checked = false,
            onCheckedChange = {

            }
        )
        SwitchPreference(
            title = stringResource(R.string.line_highlight),
            summary = stringResource(R.string.line_highlight_summary),
            checked = false,
            onCheckedChange = {

            }
        )
        SwitchPreference(
            title = stringResource(R.string.word_wrap),
            summary = stringResource(R.string.word_wrap_summary),
            checked = false,
            onCheckedChange = {

            }
        )
        SwitchPreference(
            title = stringResource(R.string.cursor_animation),
            summary = stringResource(R.string.cursor_animation_summary),
            checked = false,
            onCheckedChange = {

            }
        )
    }
}

@Composable
private fun BuildAndRunPreferenceGroup() {
    PreferenceGroup(
        text = stringResource(R.string.build_and_run)
    ) {
        SwitchPreference(
            title = "Test",
            checked = false,
            onCheckedChange = {}
        )
    }
}

@Composable
private fun ExperimentalFeaturesGroup() {
    PreferenceGroup(
        text = stringResource(R.string.experimental_features)
    ) {
        SwitchPreference(
            title = stringResource(R.string.typescript_run),
            summary = stringResource(R.string.typescript_run_summary),
            checked = false,
            onCheckedChange = {

            }
        )
    }
}

@Composable
private fun DeveloperOptionsGroup() {
    PreferenceGroup(
        text = stringResource(R.string.developer_options)
    ) {
        SwitchPreference(
            title = stringResource(R.string.record_log),
            summary = stringResource(R.string.record_log_summary),
            checked = false,
            onCheckedChange = {}
        )
    }
}

@Composable
private fun MorePreferenceGroup() {
    PreferenceGroup(
        text = stringResource(R.string.more)
    ) {
        ArrowPreference(
            title = stringResource(R.string.about),
            onClick = {

            }
        )
    }
}