package io.github.mucute.qwq.nodedev.page.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.fillSize
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.Web
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.component.CardGroup
import io.github.mucute.qwq.nodedev.shared.ui.component.CardItem
import io.github.mucute.qwq.nodedev.shared.ui.component.ExpandableCard
import io.github.mucute.qwq.nodedev.shared.ui.icons.NodeJS
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun LearningPage() {
    Column(
        Modifier
            .styleable {
                fillSize()
                contentPadding(16.dp)
            }
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(2) {
                CardGroup(
                    title = stringResource(R.string.getting_started),
                    pressFeedbackType = PressFeedbackType.Sink
                ) {
                    ExpandableCard(
                        leadingIcon = Icons.Rounded.NodeJS,
                        title = stringResource(R.string.learn_nodejs),
                        summary = stringResource(R.string.learn_nodejs_summary),
                        pressFeedbackType = PressFeedbackType.None,
                        cornerRadius = 0.dp
                    ) {
                        CardItem(
                            leadingIcon = Icons.Rounded.Android,
                            trailingIcon = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                            title = stringResource(R.string.android_platform_ui),
                            summary = stringResource(R.string.android_platform_ui_summary),
                            paddingStart = true
                        ) {

                        }
                    }
                }
            }
        }
    }
}