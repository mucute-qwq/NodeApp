package io.github.mucute.qwq.nodedev.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.ui.NavDisplay
import io.github.mucute.qwq.nodedev.screen.MainScreen

@Immutable
sealed interface Screen : NavKey {

    data object Main : Screen

}

@Composable
fun Navigation() {
    val backStack = retain { mutableStateListOf<NavKey>(Screen.Main) }

    val entryProvider = retain(backStack) {
        entryProvider<NavKey> {
            entry(Screen.Main) {
                MainScreen()
            }
        }
    }

    val entries = rememberDecoratedNavEntries(
        backStack = backStack,
        entryProvider = entryProvider
    )

    NavDisplay(
        entries = entries,
        onBack = { backStack.removeLastOrNull() }
    )
}