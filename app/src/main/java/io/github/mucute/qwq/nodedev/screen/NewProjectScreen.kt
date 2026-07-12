package io.github.mucute.qwq.nodedev.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.mucute.qwq.nodedev.shared.R
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TopAppBar

@Composable
fun NewProjectScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(R.string.new_project),
                subtitle = stringResource(R.string.new_project_summary)
            )
        }
    ) {

    }
}