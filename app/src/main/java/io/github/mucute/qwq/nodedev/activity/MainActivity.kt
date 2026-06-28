package io.github.mucute.qwq.nodedev.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.mucute.qwq.nodedev.navigation.Navigation
import io.github.mucute.qwq.nodedev.shared.ui.theme.NodeAppTheme
import io.github.mucute.qwq.nodedev.viewmodel.AppThemeMode
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppViewModel
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.ThemeColorSpec
import top.yukonga.miuix.kmp.theme.ThemeController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val nodeAppViewModel: NodeAppViewModel = viewModel()
            val nodeAppState by nodeAppViewModel.state.collectAsStateWithLifecycle()
            val appThemeMode = nodeAppState.appThemeMode
            val appThemeMonetColor = nodeAppState.appThemeMonetColor
            val themeController = retain(appThemeMode, appThemeMonetColor) {
                ThemeController(
                    colorSchemeMode = if (appThemeMonetColor) when (appThemeMode) {
                        AppThemeMode.System -> ColorSchemeMode.MonetSystem
                        AppThemeMode.Light -> ColorSchemeMode.MonetLight
                        AppThemeMode.Dark -> ColorSchemeMode.MonetDark
                    } else when (appThemeMode) {
                        AppThemeMode.System -> ColorSchemeMode.System
                        AppThemeMode.Light -> ColorSchemeMode.Light
                        AppThemeMode.Dark -> ColorSchemeMode.Dark
                    },
                    colorSpec = ThemeColorSpec.Spec2025
                )
            }
            NodeAppTheme(
                themeController
            ) {
                Navigation()
            }
        }
    }

}