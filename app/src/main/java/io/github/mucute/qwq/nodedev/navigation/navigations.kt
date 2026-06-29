package io.github.mucute.qwq.nodedev.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.platform.LocalView
import androidx.core.view.postDelayed
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.ui.NavDisplay
import io.github.mucute.qwq.nodedev.composition.local.LocalBackStack
import io.github.mucute.qwq.nodedev.screen.GuideScreen
import io.github.mucute.qwq.nodedev.screen.MainScreen
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppViewModel
import kotlinx.coroutines.Dispatchers

@Immutable
sealed interface Screen : NavKey {

    data object Guide : Screen

    data object Main : Screen

}

@Composable
fun Navigation() {
    val nodeAppViewModel: NodeAppViewModel = viewModel()
    val nodeAppState by nodeAppViewModel.state.collectAsStateWithLifecycle()
    val skipGuide = nodeAppState.skipGuide

    val backStack = retain(skipGuide) {
        mutableStateListOf<NavKey>(if (skipGuide) Screen.Main else Screen.Guide)
    }
    val entryProvider = retain(backStack) {
        entryProvider<NavKey> {

            entry(Screen.Guide) {
                GuideScreen()
            }

            entry(Screen.Main) {
                MainScreen()
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