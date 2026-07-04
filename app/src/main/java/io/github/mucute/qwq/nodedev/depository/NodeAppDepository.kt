package io.github.mucute.qwq.nodedev.depository

import android.content.Context
import androidx.core.content.edit
import io.github.mucute.qwq.nodedev.shared.application.AppContext
import io.github.mucute.qwq.nodedev.shared.mvi.Depository
import io.github.mucute.qwq.nodedev.viewmodel.AppLanguage
import io.github.mucute.qwq.nodedev.viewmodel.AppThemeMode
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppIntent
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class NodeAppDepository(
    val state: MutableStateFlow<NodeAppState>,
    val intent: Channel<NodeAppIntent>
) : Depository {

    companion object {

        const val PREFERENCE_NAME = "node_app"

        const val APP_THEME_MODE = "app_theme_mode"

        const val APP_THEME_MONET_COLOR = "app_theme_monet_color"

        const val APP_LANGUAGE = "app_language"

        const val SKIP_GUIDE = "skip_guide"

    }

    private val sharedPreferences by lazy {
        AppContext.instance.getSharedPreferences(
            PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }

    suspend fun fetchAndUpdate() = withContext(Dispatchers.IO) {
        val appThemeMode = AppThemeMode.valueOf(
            sharedPreferences.getString(
                APP_THEME_MODE,
                AppThemeMode.System.name
            )!!
        )
        val appThemeMonetColor = sharedPreferences.getBoolean(APP_THEME_MONET_COLOR, false)
        val appLanguage = AppLanguage.valueOf(
            sharedPreferences.getString(
                APP_LANGUAGE,
                AppLanguage.default.name
            )!!
        )
        val skipGuide = sharedPreferences.getBoolean(SKIP_GUIDE, false)

        state.update {
            it.copy(
                appThemeMode = appThemeMode,
                appThemeMonetColor = appThemeMonetColor,
                appLanguage = appLanguage,
                skipGuide = skipGuide
            )
        }
    }

    suspend fun changeAppTheme(appThemeMode: AppThemeMode, appThemeMonetColor: Boolean) =
        withContext(Dispatchers.IO) {
            state.update {
                it.copy(appThemeMode = appThemeMode, appThemeMonetColor = appThemeMonetColor)
            }

            sharedPreferences.edit {
                putString(APP_THEME_MODE, appThemeMode.name)
                putBoolean(APP_THEME_MONET_COLOR, appThemeMonetColor)
            }
        }

    suspend fun changeAppLanguage(appLanguage: AppLanguage) = withContext(Dispatchers.IO) {
        state.update {
            it.copy(appLanguage = appLanguage)
        }

        sharedPreferences.edit {
            putString(APP_LANGUAGE, appLanguage.name)
        }
    }

    suspend fun skipGuide(isEnabled: Boolean) = withContext(Dispatchers.IO) {
        state.update {
            it.copy(skipGuide = isEnabled)
        }

        sharedPreferences.edit {
            putBoolean(SKIP_GUIDE, isEnabled)
        }
    }

}