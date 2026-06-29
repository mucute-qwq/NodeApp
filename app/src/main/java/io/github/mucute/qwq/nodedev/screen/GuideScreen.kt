package io.github.mucute.qwq.nodedev.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.fillWidth
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.mucute.qwq.nodedev.composition.local.LocalBackStack
import io.github.mucute.qwq.nodedev.navigation.Screen
import io.github.mucute.qwq.nodedev.page.guide.FeaturesPage
import io.github.mucute.qwq.nodedev.page.guide.PermissionRequestPage
import io.github.mucute.qwq.nodedev.page.guide.StartupPage
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppIntent
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppViewModel
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Immutable
private enum class GuidePage(
    val content: @Composable () -> Unit
) {
    Startup(::StartupPage),
    PermissionRequest(::PermissionRequestPage),
    Features(::FeaturesPage)
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun GuideScreen() {
    val coroutineScope = rememberCoroutineScope()
    val backStack = LocalBackStack.current
    val pagerState =
        rememberPagerState(initialPage = GuidePage.Startup.ordinal) { GuidePage.entries.size }
    val nextPage = pagerState.currentPage + 1
    val previousPage = pagerState.currentPage - 1
    val stepProgress by animateFloatAsState(
        targetValue = nextPage.toFloat() / GuidePage.entries.size
    )
    val nodeAppViewModel: NodeAppViewModel = viewModel()
    val nodeAppIntent = nodeAppViewModel.intent

    Scaffold(
        bottomBar = {
            Row(
                Modifier
                    .styleable {
                        fillWidth()
                        contentPadding(24.dp)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Column(Modifier.size(54.dp)) {
                    AnimatedVisibility(
                        visible = pagerState.currentPage > 0,
                        enter = scaleIn() + fadeIn(),
                        exit = scaleOut(targetScale = 0.7f) + fadeOut(),
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    if (previousPage >= 0) {
                                        pagerState.animateScrollToPage(previousPage)
                                        return@launch
                                    }


                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowBack,
                                null
                            )
                        }
                    }
                }

                LinearProgressIndicator(
                    modifier = Modifier
                        .weight(1f),
                    progress = stepProgress
                )
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            if (nextPage < GuidePage.entries.size) {
                                pagerState.animateScrollToPage(nextPage)
                                return@launch
                            }

                            nodeAppIntent.send(NodeAppIntent.SkipGuide(isEnabled = true))

                            backStack += Screen.Main
                            backStack -= Screen.Guide
                        }
                    },
                    backgroundColor = MiuixTheme.colorScheme.primary,
                    minWidth = 54.dp,
                    minHeight = 54.dp
                ) {
                    AnimatedContent(
                        targetState = nextPage == GuidePage.entries.size
                    ) {
                        Icon(
                            if (it) Icons.Rounded.Done else Icons.AutoMirrored.Rounded.ArrowForward,
                            null,
                            tint = MiuixTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) { currentPage ->
            GuidePage.entries[currentPage].content()
        }
    }
}