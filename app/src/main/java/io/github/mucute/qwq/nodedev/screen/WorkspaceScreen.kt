package io.github.mucute.qwq.nodedev.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import io.github.mucute.qwq.nodedev.model.Project
import io.github.mucute.qwq.nodedev.navigation.NavScreen
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.composition.local.LocalBackStack
import io.github.mucute.qwq.nodedev.shared.composition.local.LocalSnackBarState
import io.github.rosemoe.sora.widget.CodeEditor
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.TopAppBar

@Composable
fun WorkspaceScreen(project: Project) {
    val backStack = LocalBackStack.current
    val scrollBehavior = MiuixScrollBehavior()
    val snackBarHostState = retain { SnackbarHostState() }

    CompositionLocalProvider(LocalSnackBarState provides snackBarHostState) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = project.name,
                    subtitle = stringResource(R.string.workspace),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                backStack.removeLastOrNull()
                            }
                        ) {
                            Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(snackBarHostState)
            },
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                CodeEditorComposable()
            }
        }
    }
}

@Composable
private fun CodeEditorComposable() {
    val context = LocalContext.current
    val codeEditor = remember { CodeEditor(context) }
    AndroidView(
        factory = {
            codeEditor.apply {
                isFocusedByDefault = true
            }
        },
        update = {

        },
        onRelease = {
            codeEditor.release()
        },
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    )

    DisposableEffect(codeEditor) {
        codeEditor.requestFocus()
        codeEditor.showSoftInput()

        onDispose {
            codeEditor.hideSoftInput()
        }
    }
}