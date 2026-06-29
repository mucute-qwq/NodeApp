package io.github.mucute.qwq.nodedev.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Cottage
import androidx.compose.material.icons.rounded.Extension
import androidx.compose.material.icons.rounded.Settings
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
import io.github.mucute.qwq.nodedev.page.main.LearningPage
import io.github.mucute.qwq.nodedev.page.main.SettingsPage
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.theme.NodeAppTheme
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
private enum class MainPage(
    val icon: ImageVector,
    @field:StringRes val labelResId: Int,
    val content: @Composable () -> Unit
) {

    Home(Icons.Rounded.Cottage, R.string.home, ::HomePage),
    Learning(Icons.Rounded.Book, R.string.learning, ::LearningPage),
    Extensions(Icons.Rounded.Extension, R.string.extensions, ::ExtensionsPage),
    Settings(Icons.Rounded.Settings, R.string.settings, ::SettingsPage)

}

@Composable
fun MainScreen() {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = MiuixScrollBehavior()
    val snackBarHostState = retain { SnackbarHostState() }
    val pagerState =
        rememberPagerState(initialPage = MainPage.Home.ordinal) { MainPage.entries.size }

    CompositionLocalProvider(LocalSnackBarState provides snackBarHostState) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = stringResource(R.string.app_name),
                    subtitle = stringResource(MainPage.entries[pagerState.targetPage].labelResId),
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                NavigationBar(
                    mode = NavigationBarDisplayMode.IconWithSelectedLabel
                ) {
                    MainPage.entries.forEach { page ->
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
                MainPage.entries[currentPage].content()
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    NodeAppTheme {
        MainScreen()
    }
}