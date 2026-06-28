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
val Icons.Rounded.Syringe: ImageVector
    get() {
        if (_syringe != null) {
            return _syringe!!
        }
        _syringe =
            ImageVector.Builder(
                name = "syringe",
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
                        moveTo(3.9f, 11.18f)
                        quadTo(3.63f, 10.88f, 3.63f, 10.46f)
                        reflectiveQuadTo(3.9f, 9.75f)
                        lineTo(6.7f, 6.95f)
                        lineTo(5.63f, 5.88f)
                        lineToRelative(-0.3f, 0.3f)
                        quadToRelative(-0.3f, 0.3f, -0.71f, 0.3f)
                        reflectiveQuadTo(3.9f, 6.18f)
                        quadTo(3.63f, 5.88f, 3.63f, 5.46f)
                        reflectiveQuadTo(3.9f, 4.77f)
                        lineToRelative(2f, -2f)
                        quadTo(6.2f, 2.47f, 6.61f, 2.47f)
                        reflectiveQuadToRelative(0.71f, 0.3f)
                        quadToRelative(0.3f, 0.27f, 0.3f, 0.7f)
                        reflectiveQuadToRelative(-0.3f, 0.7f)
                        lineToRelative(-0.3f, 0.3f)
                        lineTo(8.1f, 5.55f)
                        lineToRelative(2.8f, -2.8f)
                        quadToRelative(0.3f, -0.3f, 0.71f, -0.3f)
                        reflectiveQuadToRelative(0.71f, 0.3f)
                        reflectiveQuadToRelative(0.3f, 0.71f)
                        reflectiveQuadToRelative(-0.3f, 0.71f)
                        lineTo(11.65f, 4.82f)
                        lineTo(13.2f, 6.38f)
                        lineToRelative(-2.83f, 2.8f)
                        quadTo(10.1f, 9.48f, 10.1f, 9.89f)
                        reflectiveQuadToRelative(0.27f, 0.71f)
                        quadToRelative(0.3f, 0.3f, 0.71f, 0.3f)
                        reflectiveQuadTo(11.8f, 10.6f)
                        lineTo(14.6f, 7.77f)
                        lineToRelative(1.52f, 1.5f)
                        lineTo(13.3f, 12.1f)
                        quadTo(13f, 12.4f, 13f, 12.81f)
                        reflectiveQuadToRelative(0.3f, 0.71f)
                        quadToRelative(0.28f, 0.28f, 0.69f, 0.26f)
                        quadTo(14.4f, 13.77f, 14.7f, 13.5f)
                        lineToRelative(2.8f, -2.83f)
                        lineToRelative(1.53f, 1.53f)
                        quadToRelative(0.57f, 0.57f, 0.57f, 1.41f)
                        reflectiveQuadToRelative(-0.57f, 1.41f)
                        lineToRelative(-0.7f, 0.72f)
                        lineTo(22.2f, 19.6f)
                        quadToRelative(0.25f, 0.25f, 0.13f, 0.55f)
                        reflectiveQuadToRelative(-0.48f, 0.3f)
                        horizontalLineTo(20.83f)
                        quadToRelative(-0.3f, 0f, -0.59f, -0.13f)
                        reflectiveQuadTo(19.75f, 20f)
                        lineTo(16.9f, 17.15f)
                        lineToRelative(-0.7f, 0.73f)
                        quadToRelative(-0.57f, 0.57f, -1.41f, 0.57f)
                        reflectiveQuadTo(13.38f, 17.88f)
                        lineTo(6f, 10.5f)
                        lineTo(5.33f, 11.18f)
                        quadToRelative(-0.3f, 0.28f, -0.71f, 0.28f)
                        reflectiveQuadTo(3.9f, 11.18f)
                        close()
                    }
                }
                .build()
        return _syringe!!
    }

private var _syringe: ImageVector? = null
