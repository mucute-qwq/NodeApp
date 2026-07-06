package io.github.mucute.qwq.nodedev.shared.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardColors
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.menu.WindowIconDropdownMenu
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@Composable
inline fun CardGroup(
    title: String,
    cornerRadius: Dp = CardDefaults.CornerRadius,
    pressFeedbackType: PressFeedbackType = PressFeedbackType.None,
    showIndication: Boolean = false,
    colors: CardColors = CardDefaults.defaultColors(),
    crossinline content: @Composable ColumnScope.() -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        SmallTitle(title)

        Card(
            cornerRadius = cornerRadius,
            pressFeedbackType = pressFeedbackType,
            showIndication = showIndication,
            colors = colors
        ) {
            content()
        }
    }
}

@Composable
inline fun ExpandableCard(
    leadingIcon: ImageVector? = null,
    cornerRadius: Dp = CardDefaults.CornerRadius,
    pressFeedbackType: PressFeedbackType = PressFeedbackType.Sink,
    showIndication: Boolean = false,
    colors: CardColors = CardDefaults.defaultColors(),
    title: String,
    summary: String? = null,
    crossinline content: @Composable ColumnScope.() -> Unit
) {
    var expanded by retain { mutableStateOf(false) }
    val degrees by animateFloatAsState(
        if (expanded) -180f else 0f
    )

    Card(
        Modifier
            .fillMaxWidth(),
        cornerRadius = cornerRadius,
        pressFeedbackType = pressFeedbackType,
        showIndication = showIndication,
        colors = colors,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            BasicCardItem(
                leadingIcon = {
                    leadingIcon?.let {
                        Icon(it, null, modifier = Modifier.size(24.dp))
                    }
                },
                trailingIcon = {
                    Icon(
                        Icons.Rounded.KeyboardArrowDown,
                        null,
                        modifier = Modifier
                            .size(24.dp)
                            .rotate(degrees)
                    )
                },
                cornerRadius = 0.dp,
                pressFeedbackType = PressFeedbackType.None,
                showIndication = true,
                colors = colors,
                title = title,
                summary = summary
            ) {
                expanded = !expanded
            }
            if (expanded) {
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                content()
            }
        }
    }
}

@Composable
inline fun BasicCardItem(
    crossinline leadingIcon: @Composable () -> Unit,
    crossinline trailingIcon: @Composable () -> Unit,
    cornerRadius: Dp = 0.dp,
    pressFeedbackType: PressFeedbackType = PressFeedbackType.None,
    showIndication: Boolean = true,
    colors: CardColors = CardDefaults.defaultColors(),
    title: String,
    summary: String? = null,
    crossinline onClick: () -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth(),
        colors = colors,
        cornerRadius = cornerRadius,
        pressFeedbackType = pressFeedbackType,
        insideMargin = PaddingValues(16.dp),
        showIndication = showIndication,
        onClick = { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            leadingIcon()
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    style = MiuixTheme.textStyles.title3,
                    fontWeight = FontWeight.Bold
                )
                summary?.let {
                    Text(
                        it,
                        style = MiuixTheme.textStyles.body2
                    )
                }
            }
            trailingIcon()
        }
    }
}

@Composable
inline fun CardItem(
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    cornerRadius: Dp = 0.dp,
    iconSize: Dp = 24.dp,
    pressFeedbackType: PressFeedbackType = PressFeedbackType.None,
    showIndication: Boolean = true,
    colors: CardColors = CardDefaults.defaultColors(),
    title: String,
    summary: String? = null,
    crossinline onClick: () -> Unit
) {
    BasicCardItem(
        leadingIcon = {
            leadingIcon?.let {
                Icon(it, null, modifier = Modifier.size(iconSize))
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                Icon(it, null, modifier = Modifier.size(iconSize))
            }
        },
        cornerRadius = cornerRadius,
        pressFeedbackType = pressFeedbackType,
        showIndication = showIndication,
        colors = colors,
        title = title,
        summary = summary,
        onClick = onClick
    )
}

@Composable
inline fun IconButtonCardItem(
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    cornerRadius: Dp = 0.dp,
    pressFeedbackType: PressFeedbackType = PressFeedbackType.None,
    showIndication: Boolean = true,
    colors: CardColors = CardDefaults.defaultColors(),
    title: String,
    summary: String? = null,
    crossinline onClick: () -> Unit,
    crossinline onIconClick: () -> Unit
) {
    BasicCardItem(
        leadingIcon = {
            leadingIcon?.let {
                Icon(it, null)
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                IconButton(
                    onClick = {
                        onIconClick()
                    }
                ) {
                    Icon(it, null)
                }
            }
        },
        cornerRadius = cornerRadius,
        pressFeedbackType = pressFeedbackType,
        showIndication = showIndication,
        colors = colors,
        title = title,
        summary = summary,
        onClick = onClick
    )
}

@Composable
inline fun ActionCardItem(
    entry: DropdownEntry,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    cornerRadius: Dp = 0.dp,
    pressFeedbackType: PressFeedbackType = PressFeedbackType.None,
    showIndication: Boolean = true,
    colors: CardColors = CardDefaults.defaultColors(),
    title: String,
    summary: String? = null,
    crossinline onClick: () -> Unit
) {
    BasicCardItem(
        leadingIcon = {
            leadingIcon?.let {
                Icon(it, null)
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                WindowIconDropdownMenu(
                    entry = entry
                ) {
                    Icon(Icons.Rounded.MoreVert, null)
                }
            }
        },
        cornerRadius = cornerRadius,
        pressFeedbackType = pressFeedbackType,
        showIndication = showIndication,
        colors = colors,
        title = title,
        summary = summary,
        onClick = onClick
    )
}