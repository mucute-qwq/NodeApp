package io.github.mucute.qwq.nodedev.page.guide

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NetworkPing
import androidx.compose.material.icons.rounded.Storage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.component.CardItem
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@Immutable
private data class PermissionItem(
    val icon: ImageVector,
    @param:StringRes val titleResId: Int,
    @param:StringRes val summaryResId: Int
)

private val permissionItems = listOf(
    PermissionItem(
        icon = Icons.Rounded.Storage,
        titleResId = R.string.storage_permission,
        summaryResId = R.string.storage_permission_summary
    ),
    PermissionItem(
        icon = Icons.Rounded.NetworkPing,
        titleResId = R.string.network_permission,
        summaryResId = R.string.network_permission_summary
    )
)

@Composable
fun PermissionRequestPage() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(R.string.permission_request),
                subtitle = stringResource(R.string.permission_request_summary)
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
                items(permissionItems.size) { index ->
                    val permissionItem = permissionItems[index]
                    CardItem(
                        leadingIcon = permissionItem.icon,
                        title = stringResource(permissionItem.titleResId),
                        summary = stringResource(permissionItem.summaryResId),
                        cornerRadius = CardDefaults.CornerRadius,
                        pressFeedbackType = PressFeedbackType.Sink
                    ) {}
                }
            }
        }
    }
}