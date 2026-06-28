@file:Suppress("ObjectPropertyName")

package io.github.mucute.qwq.nodedev.shared.ui.icons

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Suppress("CheckReturnValue")
val Icons.Rounded.Package2: ImageVector
    get() {
        if (_package_2 != null) {
            return _package_2!!
        }
        _package_2 =
            ImageVector.Builder(
                name = "package_2",
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
                        moveTo(11f, 21.73f)
                        verticalLineTo(12.58f)
                        lineTo(3f, 7.95f)
                        verticalLineToRelative(8.03f)
                        quadToRelative(0f, 0.55f, 0.26f, 1f)
                        reflectiveQuadTo(4f, 17.7f)
                        lineToRelative(7f, 4.03f)
                        close()
                        moveToRelative(2f, 0f)
                        lineTo(20f, 17.7f)
                        quadToRelative(0.48f, -0.27f, 0.74f, -0.72f)
                        quadTo(21f, 16.52f, 21f, 15.98f)
                        verticalLineTo(7.95f)
                        lineToRelative(-8f, 4.63f)
                        verticalLineToRelative(9.15f)
                        close()
                        moveTo(16.98f, 7.97f)
                        lineTo(19.93f, 6.25f)
                        lineTo(13f, 2.27f)
                        quadTo(12.53f, 2f, 12f, 2f)
                        reflectiveQuadTo(11f, 2.27f)
                        lineTo(9.03f, 3.4f)
                        lineToRelative(7.95f, 4.57f)
                        close()
                        moveTo(12f, 10.85f)
                        lineToRelative(2.98f, -1.7f)
                        lineTo(7.05f, 4.55f)
                        lineToRelative(-3f, 1.73f)
                        lineTo(12f, 10.85f)
                        close()
                    }
                }
                .build()
        return _package_2!!
    }

private var _package_2: ImageVector? = null
