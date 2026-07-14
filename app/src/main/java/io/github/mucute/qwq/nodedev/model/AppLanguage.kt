package io.github.mucute.qwq.nodedev.model

import androidx.compose.runtime.Immutable
import java.util.Locale

@Immutable
enum class AppLanguage(val locale: Locale) {
    English(Locale.ENGLISH),
    Chinese(Locale.CHINESE);

    companion object {

        val default: AppLanguage
            get() {
                val locale = Locale.getDefault()
                return entries.find { it.locale.language == locale.language } ?: English
            }

    }

}