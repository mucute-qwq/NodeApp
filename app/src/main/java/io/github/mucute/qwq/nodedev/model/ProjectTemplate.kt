package io.github.mucute.qwq.nodedev.model

import androidx.annotation.StringRes
import io.github.mucute.qwq.nodedev.shared.R

enum class ProjectTemplate(
    @param:StringRes val templateResId: Int,
    val path: String
) {

    Default(
        templateResId = R.string.default_project_template,
        path = "template/default.zip"
    ),

    Vite(
        templateResId = R.string.vite_project_template,
        path = "template/vite.zip"
    );

}