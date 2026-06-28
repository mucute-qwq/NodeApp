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
val Icons.Rounded.Layers: ImageVector
    get() {
        if (_layers != null) {
            return _layers!!
        }
        _layers =
            ImageVector.Builder(
                name = "layers",
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
                        moveTo(4.03f, 14.85f)
                        quadTo(3.63f, 14.55f, 3.64f, 14.06f)
                        reflectiveQuadTo(4.05f, 13.27f)
                        quadToRelative(0.28f, -0.2f, 0.6f, -0.2f)
                        reflectiveQuadToRelative(0.6f, 0.2f)
                        lineTo(12f, 18.5f)
                        lineToRelative(6.75f, -5.23f)
                        quadToRelative(0.28f, -0.2f, 0.6f, -0.2f)
                        reflectiveQuadToRelative(0.6f, 0.2f)
                        quadToRelative(0.4f, 0.3f, 0.41f, 0.79f)
                        reflectiveQuadToRelative(-0.39f, 0.79f)
                        lineTo(13.23f, 20.1f)
                        quadTo(12.68f, 20.53f, 12f, 20.53f)
                        reflectiveQuadTo(10.78f, 20.1f)
                        lineTo(4.03f, 14.85f)
                        close()
                        moveToRelative(6.75f, 0.2f)
                        lineTo(5.03f, 10.58f)
                        quadTo(4.25f, 9.98f, 4.25f, 9f)
                        quadToRelative(0f, -0.98f, 0.78f, -1.57f)
                        lineTo(10.78f, 2.95f)
                        quadTo(11.33f, 2.52f, 12f, 2.52f)
                        reflectiveQuadToRelative(1.23f, 0.43f)
                        lineToRelative(5.75f, 4.48f)
                        quadTo(19.75f, 8.02f, 19.75f, 9f)
                        reflectiveQuadToRelative(-0.77f, 1.57f)
                        lineToRelative(-5.75f, 4.47f)
                        quadTo(12.68f, 15.48f, 12f, 15.48f)
                        reflectiveQuadTo(10.78f, 15.05f)
                        close()
                        moveTo(12f, 13.45f)
                        lineTo(17.75f, 9f)
                        lineTo(12f, 4.55f)
                        lineTo(6.25f, 9f)
                        lineTo(12f, 13.45f)
                        close()
                        moveTo(12f, 9f)
                        close()
                    }
                }
                .build()
        return _layers!!
    }

private var _layers: ImageVector? = null
