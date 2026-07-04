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
val Icons.Rounded.NestEcoLeaf: ImageVector
    get() {
        if (_nest_eco_leaf != null) {
            return _nest_eco_leaf!!
        }
        _nest_eco_leaf =
            ImageVector.Builder(
                name = "nest_eco_leaf",
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
                        moveTo(12f, 20f)
                        quadTo(10.6f, 20f, 9.36f, 19.56f)
                        reflectiveQuadTo(7.1f, 18.33f)
                        lineTo(5.7f, 19.7f)
                        quadTo(5.43f, 19.98f, 5f, 19.98f)
                        quadToRelative(-0.42f, 0f, -0.7f, -0.28f)
                        quadTo(4.03f, 19.43f, 4.03f, 19f)
                        reflectiveQuadTo(4.3f, 18.3f)
                        lineTo(5.68f, 16.93f)
                        quadTo(4.88f, 15.9f, 4.44f, 14.65f)
                        reflectiveQuadTo(4f, 12f)
                        quadTo(4f, 8.65f, 6.33f, 6.32f)
                        reflectiveQuadTo(12f, 4f)
                        horizontalLineToRelative(6f)
                        quadToRelative(0.82f, 0f, 1.41f, 0.59f)
                        quadTo(20f, 5.18f, 20f, 6f)
                        verticalLineToRelative(6f)
                        quadToRelative(0f, 3.35f, -2.32f, 5.68f)
                        reflectiveQuadTo(12f, 20f)
                        close()
                        moveToRelative(0f, -2f)
                        quadToRelative(2.5f, 0f, 4.25f, -1.75f)
                        reflectiveQuadTo(18f, 12f)
                        verticalLineTo(6f)
                        horizontalLineTo(12f)
                        quadTo(9.5f, 6f, 7.75f, 7.75f)
                        reflectiveQuadTo(6f, 12f)
                        quadToRelative(0f, 0.97f, 0.3f, 1.86f)
                        reflectiveQuadToRelative(0.82f, 1.61f)
                        lineTo(12.3f, 10.3f)
                        quadTo(12.58f, 10.02f, 13f, 10.02f)
                        reflectiveQuadToRelative(0.7f, 0.28f)
                        quadTo(14f, 10.6f, 14f, 11.01f)
                        reflectiveQuadToRelative(-0.3f, 0.71f)
                        lineTo(8.53f, 16.9f)
                        quadToRelative(0.72f, 0.53f, 1.61f, 0.81f)
                        reflectiveQuadTo(12f, 18f)
                        close()
                        moveToRelative(0f, -6f)
                        close()
                    }
                }
                .build()
        return _nest_eco_leaf!!
    }

private var _nest_eco_leaf: ImageVector? = null
