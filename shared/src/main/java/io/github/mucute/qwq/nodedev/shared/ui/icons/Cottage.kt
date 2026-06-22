@file:Suppress("ObjectPropertyName")

package io.github.mucute.qwq.nodedev.shared.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

@Suppress("CheckReturnValue")
val MiuixIcons.Cottage: ImageVector
    get() {
        if (_cottage != null) {
            return _cottage!!
        }
        _cottage =
            ImageVector.Builder(
                name = "cottage",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.NonZero,
                    ) {
                        moveTo(4f, 21f)
                        verticalLineTo(11.63f)
                        lineTo(2.2f, 13f)
                        lineTo(1f, 11.4f)
                        lineTo(4f, 9.1f)
                        verticalLineTo(6f)
                        horizontalLineTo(6f)
                        verticalLineTo(7.57f)
                        lineTo(12f, 3f)
                        lineToRelative(11f, 8.4f)
                        lineToRelative(-1.2f, 1.58f)
                        lineTo(20f, 11.63f)
                        verticalLineTo(21f)
                        horizontalLineTo(4f)
                        close()
                        moveTo(6f, 19f)
                        horizontalLineToRelative(5f)
                        verticalLineTo(15f)
                        horizontalLineToRelative(2f)
                        verticalLineToRelative(4f)
                        horizontalLineToRelative(5f)
                        verticalLineTo(10.1f)
                        lineTo(12f, 5.52f)
                        lineTo(6f, 10.1f)
                        verticalLineTo(19f)
                        close()
                        moveTo(4f, 5f)
                        quadTo(4f, 3.75f, 4.88f, 2.88f)
                        reflectiveQuadTo(7f, 2f)
                        quadTo(7.43f, 2f, 7.71f, 1.71f)
                        reflectiveQuadTo(8f, 1f)
                        horizontalLineToRelative(2f)
                        quadTo(10f, 2.25f, 9.13f, 3.13f)
                        reflectiveQuadTo(7f, 4f)
                        quadTo(6.58f, 4f, 6.29f, 4.29f)
                        reflectiveQuadTo(6f, 5f)
                        horizontalLineTo(4f)
                        close()
                        moveTo(6f, 19f)
                        horizontalLineToRelative(5f)
                        horizontalLineToRelative(2f)
                        horizontalLineToRelative(5f)
                        horizontalLineTo(12f)
                        horizontalLineTo(6f)
                        close()
                    }
                }
                .build()
        return _cottage!!
    }

private var _cottage: ImageVector? = null
