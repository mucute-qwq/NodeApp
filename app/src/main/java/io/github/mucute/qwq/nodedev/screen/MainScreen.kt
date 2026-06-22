package io.github.mucute.qwq.nodedev.screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import io.github.mucute.qwq.nodedev.composition.local.LocalSnackBarState
import io.github.mucute.qwq.nodedev.page.main.ExtensionsPage
import io.github.mucute.qwq.nodedev.page.main.HomePage
import io.github.mucute.qwq.nodedev.page.main.InstancesPage
import io.github.mucute.qwq.nodedev.page.main.SettingsPage
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.icons.Cottage
import io.github.mucute.qwq.nodedev.shared.ui.icons.Extension
import top.yukonga.miuix.kmp.basic.FloatingNavigationBar
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarItem
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarDisplayMode
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Blocklist
import top.yukonga.miuix.kmp.icon.extended.ListView
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.icon.extended.Settings

@Immutable
private enum class Page(
    val icon: ImageVector,
    @field:StringRes val labelResId: Int,
    val content: @Composable () -> Unit
) {

    Home(MiuixIcons.Cottage, R.string.home, ::HomePage),
    Instances(MiuixIcons.ListView, R.string.instances, ::InstancesPage),
    Extensions(MiuixIcons.Extension, R.string.extensions, ::ExtensionsPage),
    Settings(MiuixIcons.Settings, R.string.settings, ::SettingsPage)

}

@Composable
fun MainScreen() {
    var selectedPage by retain { mutableStateOf(Page.Home) }
    val snackBarHostState = retain { SnackbarHostState() }
    CompositionLocalProvider(LocalSnackBarState provides snackBarHostState) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = stringResource(R.string.app_name)
                )
            },
            bottomBar = {
                NavigationBar(
                    mode = NavigationBarDisplayMode.IconWithSelectedLabel
                ) {
                    Page.entries.forEach { page ->
                        NavigationBarItem(
                            selected = page === selectedPage,
                            onClick = { selectedPage = page },
                            icon = page.icon,
                            label = stringResource(page.labelResId)
                        )
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(snackBarHostState)
            }
        ) {
            selectedPage.content()
        }
    }
}