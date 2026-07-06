package io.github.mucute.qwq.nodedev.page.guide

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.component.CardItem
import io.github.mucute.qwq.nodedev.shared.ui.icons.NodeJS
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@Immutable
private data class FeatureItem(
    val icon: ImageVector,
    @param:StringRes val titleResId: Int,
    @param:StringRes val summaryResId: Int
)

private val featureItems = listOf(
    FeatureItem(
        icon = Icons.Rounded.NodeJS,
        titleResId = R.string.nodejs_title,
        summaryResId = R.string.nodejs_summary
    )
)

@Composable
fun FeaturesPage() {
    Scaffold(
        Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = stringResource(R.string.common_features_title),
                subtitle = stringResource(R.string.common_features_summary)
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(featureItems.size) { index ->
                    val featureItem = featureItems[index]
                    CardItem(
                        leadingIcon = featureItem.icon,
                        title = stringResource(featureItem.titleResId),
                        summary = stringResource(featureItem.summaryResId),
                        cornerRadius = CardDefaults.CornerRadius,
                        pressFeedbackType = PressFeedbackType.Sink
                    ) {}
                }
            }
        }
    }
}