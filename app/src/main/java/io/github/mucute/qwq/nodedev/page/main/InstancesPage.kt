package io.github.mucute.qwq.nodedev.page.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.fillSize
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.component.ExpandableCard
import io.github.mucute.qwq.nodedev.shared.ui.component.CardItem
import io.github.mucute.qwq.nodedev.shared.ui.icons.Android
import io.github.mucute.qwq.nodedev.shared.ui.icons.Layers
import io.github.mucute.qwq.nodedev.shared.ui.icons.Web

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun InstancesPage() {
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
            items(1) {
                ExpandableCard(
                    leadingIcon = Icons.Rounded.Layers,
                    title = stringResource(R.string.ui_and_ux),
                    summary = stringResource(R.string.ui_and_ux_summary)
                ) {
                    CardItem(
                        leadingIcon = Icons.Rounded.Android,
                        trailingIcon = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        title = stringResource(R.string.android_platform_ui),
                        summary = stringResource(R.string.android_platform_ui_summary)
                    ) {

                    }
                    CardItem(
                        leadingIcon = Icons.Rounded.Web,
                        trailingIcon = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                        title = stringResource(R.string.web_platform_ui),
                        summary = stringResource(R.string.web_platform_ui_summary)
                    ) {

                    }
                }
            }
        }
    }
}