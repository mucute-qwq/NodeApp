package io.github.mucute.qwq.nodedev.page.guide

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.mucute.qwq.nodedev.shared.R
import io.github.mucute.qwq.nodedev.shared.ui.theme.NodeAppIcon
import io.github.mucute.qwq.nodedev.shared.ui.theme.NodeAppIconBackgroundColor
import io.github.mucute.qwq.nodedev.shared.ui.theme.NodeAppIconForegroundColor
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun StartupPage() {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                NodeAppIcon,
                null,
                tint = NodeAppIconForegroundColor,
                modifier = Modifier
                    .size(150.dp)
                    .background(NodeAppIconBackgroundColor, CircleShape)
                    .scale(0.5f)
            )
            Text(
                stringResource(R.string.app_name),
                style = MiuixTheme.textStyles.title1,
                fontWeight = FontWeight.Bold
            )
            Text(
                stringResource(R.string.app_description),
                style = MiuixTheme.textStyles.body1,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}