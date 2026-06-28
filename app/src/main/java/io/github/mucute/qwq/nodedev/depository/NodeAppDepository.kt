package io.github.mucute.qwq.nodedev.depository

import android.content.Context
import androidx.core.content.edit
import io.github.mucute.qwq.nodedev.application.AppContext
import io.github.mucute.qwq.nodedev.mvi.Depository
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

    }

    suspend fun fetchAndUpdate() = withContext(Dispatchers.IO) {
        state.update {
            val sharedPreferences =
                AppContext.instance.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            val appThemeMode = AppThemeMode.valueOf(
                sharedPreferences.getString(
                    APP_THEME_MODE,
                    AppThemeMode.System.name
                )!!
            )
            val appThemeMonetColor = sharedPreferences.getBoolean(APP_THEME_MONET_COLOR, false)

            it.copy(appThemeMode = appThemeMode, appThemeMonetColor = appThemeMonetColor)
        }
    }

    suspend fun changeAppTheme(appThemeMode: AppThemeMode, appThemeMonetColor: Boolean) =
        withContext(
            Dispatchers.IO
        ) {
            state.update {
                it.copy(appThemeMode = appThemeMode, appThemeMonetColor = appThemeMonetColor)
            }

            val sharedPreferences =
                AppContext.instance.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            sharedPreferences.edit {
                putString(APP_THEME_MODE, appThemeMode.name)
                putBoolean(APP_THEME_MONET_COLOR, appThemeMonetColor)
            }
        }

}