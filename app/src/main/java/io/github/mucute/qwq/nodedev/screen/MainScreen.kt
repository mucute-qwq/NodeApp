package io.github.mucute.qwq.nodedev.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.mucute.qwq.nodedev.composition.local.LocalSnackBarState
import io.github.mucute.qwq.nodedev.page.main.ExtensionsPage
import io.github.mucute.qwq.nodedev.page.main.HomePage
import io.github.mucute.qwq.nodedev.page.main.InstancesPage
import io.github.mucute.qwq.nodedev.page.main.SettingsPage
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.icons.Cottage
import io.github.mucute.qwq.nodedev.shared.ui.icons.Extension
import io.github.mucute.qwq.nodedev.shared.ui.icons.FormatListBulleted
import io.github.mucute.qwq.nodedev.shared.ui.icons.Settings
import io.github.mucute.qwq.nodedev.shared.ui.theme.NodeDevTheme
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarDisplayMode
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.TopAppBar

@Immutable
private enum class Page(
    val icon: ImageVector,
    @field:StringRes val labelResId: Int,
    val content: @Composable () -> Unit
) {

    Home(Icons.Rounded.Cottage, R.string.home, ::HomePage),
    Instances(Icons.Rounded.FormatListBulleted, R.string.instances, ::InstancesPage),
    Extensions(Icons.Rounded.Extension, R.string.extensions, ::ExtensionsPage),
    Settings(Icons.Rounded.Settings, R.string.settings, ::SettingsPage)

}

@Composable
fun MainScreen() {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = MiuixScrollBehavior()
    val snackBarHostState = retain { SnackbarHostState() }
    val pagerState =
        rememberPagerState(initialPage = 0) { Page.entries.size }

    CompositionLocalProvider(LocalSnackBarState provides snackBarHostState) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = stringResource(R.string.app_name),
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                NavigationBar(
                    mode = NavigationBarDisplayMode.IconWithSelectedLabel
                ) {
                    Page.entries.forEach { page ->
                        NavigationBarItem(
                            selected = page.ordinal == pagerState.targetPage,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(page.ordinal)
                                }
                            },
                            icon = page.icon,
                            label = stringResource(page.labelResId)
                        )
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(snackBarHostState)
            },
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) { currentPage ->
                Page.entries[currentPage].content()
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    NodeDevTheme {
        MainScreen()
    }
}