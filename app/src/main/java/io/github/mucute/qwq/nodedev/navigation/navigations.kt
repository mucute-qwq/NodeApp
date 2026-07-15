package io.github.mucute.qwq.nodedev.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.ui.NavDisplay
import io.github.mucute.qwq.nodedev.screen.GuideScreen
import io.github.mucute.qwq.nodedev.screen.MainScreen
import io.github.mucute.qwq.nodedev.screen.NewProjectScreen
import io.github.mucute.qwq.nodedev.screen.WorkspaceScreen
import io.github.mucute.qwq.nodedev.shared.composition.local.LocalBackStack
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppViewModel

@Composable
fun Navigation() {
    val nodeAppViewModel: NodeAppViewModel = viewModel()
    val nodeAppState by nodeAppViewModel.state.collectAsStateWithLifecycle()
    val skipGuide = nodeAppState.skipGuide

    val backStack = retain(skipGuide) {
        mutableStateListOf<NavKey>(if (skipGuide) NavScreen.Main else NavScreen.Guide)
    }
    val entryProvider = retain(backStack) {
        entryProvider<NavKey> {

            entry(NavScreen.Guide) {
                GuideScreen()
            }

            entry(NavScreen.Main) {
                MainScreen()
            }

            entry(NavScreen.NewProject) {
                NewProjectScreen()
            }

            entry<NavScreen.Workspace> {
                WorkspaceScreen(it.project)
            }

        }
    }

    val entries = rememberDecoratedNavEntries(
        backStack = backStack,
        entryProvider = entryProvider
    )

    CompositionLocalProvider(LocalBackStack provides backStack) {
        NavDisplay(
            entries = entries,
            onBack = {
                backStack.removeLastOrNull()
            }
        )
    }
}