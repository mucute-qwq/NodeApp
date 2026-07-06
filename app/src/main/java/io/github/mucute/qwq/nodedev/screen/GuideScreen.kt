package io.github.mucute.qwq.nodedev.screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.mucute.qwq.nodedev.page.guide.FeaturesPage
import io.github.mucute.qwq.nodedev.page.guide.PermissionRequestPage
import io.github.mucute.qwq.nodedev.page.guide.StartupPage
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.composition.local.LocalBackStack
import io.github.mucute.qwq.nodedev.shared.navigation.NavScreen
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppIntent
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppViewModel
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.FloatingActionButton
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Immutable
private enum class GuidePage(
    val content: @Composable () -> Unit
) {
    Startup(::StartupPage),
    PermissionRequest(::PermissionRequestPage),
    Features(::FeaturesPage)
}

private val permissions = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun GuideScreen() {
    val coroutineScope = rememberCoroutineScope()
    val backStack = LocalBackStack.current
    val nodeAppViewModel: NodeAppViewModel = viewModel()
    val nodeAppIntent = nodeAppViewModel.intent
    val pagerState =
        rememberPagerState(initialPage = GuidePage.Startup.ordinal) { GuidePage.entries.size }
    val currentPage = pagerState.currentPage
    val isBeginning = currentPage == 0
    val isEnd = currentPage + 1 == GuidePage.entries.size
    val stepProgress by animateFloatAsState(
        targetValue = (currentPage.toFloat() + 1f) / GuidePage.entries.size
    )
    var isPermissionGranted by remember { mutableStateOf(false) }
    val activityResultLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (false in it.values) {
                isPermissionGranted = false
                return@rememberLauncherForActivityResult
            }
            isPermissionGranted = true
            coroutineScope.launch {
                pagerState.animateScrollToPage(currentPage + 1)
            }
        }

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
                        visible = !isBeginning,
                        enter = scaleIn() + fadeIn(),
                        exit = scaleOut(targetScale = 0.7f) + fadeOut(),
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(currentPage - 1)
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
                FloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            if (currentPage == GuidePage.PermissionRequest.ordinal) {
                                activityResultLauncher.launch(permissions)
                                if (!isPermissionGranted) {
                                    return@launch
                                }
                            }

                            if (!isEnd) {
                                pagerState.animateScrollToPage(currentPage + 1)
                                return@launch
                            }

                            nodeAppIntent.send(NodeAppIntent.SkipGuide(isEnabled = true))

                            backStack += NavScreen.Main
                            backStack -= NavScreen.Guide
                        }
                    },
                    minWidth = 54.dp,
                    minHeight = 54.dp
                ) {
                    AnimatedContent(
                        targetState = currentPage
                    ) {
                        if (it == GuidePage.PermissionRequest.ordinal) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp),
                            ) {
                                Icon(
                                    Icons.Rounded.Lock,
                                    null,
                                    tint = MiuixTheme.colorScheme.onPrimary
                                )
                                Text(
                                    stringResource(R.string.grant_permissions),
                                    color = MiuixTheme.colorScheme.onPrimary
                                )
                            }
                        } else {
                            val isEnd = it + 1 == GuidePage.entries.size
                            AnimatedContent(
                                targetState = isEnd
                            ) { isEnd ->
                                Icon(
                                    if (isEnd) Icons.Rounded.Done else Icons.AutoMirrored.Rounded.ArrowForward,
                                    null,
                                    tint = MiuixTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = currentPage != GuidePage.PermissionRequest.ordinal,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) { currentPage ->
            GuidePage.entries[currentPage].content()
        }
    }
}