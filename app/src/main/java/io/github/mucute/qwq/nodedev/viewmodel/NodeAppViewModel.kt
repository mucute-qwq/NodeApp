package io.github.mucute.qwq.nodedev.viewmodel

import io.github.mucute.qwq.nodedev.depository.NodeAppDepository
import io.github.mucute.qwq.nodedev.shared.mvi.MVIViewModel
import io.github.mucute.qwq.nodedev.shared.mvi.UIIntent
import io.github.mucute.qwq.nodedev.shared.mvi.UIState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Locale

enum class AppThemeMode {
    System, Light, Dark
}

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

data class NodeAppState(
    val appThemeMode: AppThemeMode = AppThemeMode.System,
    val appThemeMonetColor: Boolean = false,
    val appLanguage: AppLanguage = AppLanguage.English,
    val skipGuide: Boolean = false
) : UIState

sealed interface NodeAppIntent : UIIntent {

    data class ChangeAppThemeMode(
        val appThemeMode: AppThemeMode,
        val appThemeMonetColor: Boolean
    ) : NodeAppIntent

    data class ChangeAppLanguage(
        val appLanguage: AppLanguage
    ) : NodeAppIntent

    data class SkipGuide(
        val isEnabled: Boolean
    ) : NodeAppIntent

}

class NodeAppViewModel : MVIViewModel<NodeAppState, NodeAppIntent, NodeAppDepository>() {

    override fun initialDepository(
        state: MutableStateFlow<NodeAppState>,
        intent: Channel<NodeAppIntent>
    ) = NodeAppDepository(state, intent)


    override fun initialState() = NodeAppState()

    init {
        runBlocking {
            _depository.fetchAndUpdate()
        }
    }

    override suspend fun onIntent(intent: NodeAppIntent) {
        when (intent) {
            is NodeAppIntent.ChangeAppThemeMode -> _depository.changeAppTheme(intent.appThemeMode, intent.appThemeMonetColor)

            is NodeAppIntent.ChangeAppLanguage -> _depository.changeAppLanguage(intent.appLanguage)

            is NodeAppIntent.SkipGuide -> _depository.skipGuide(intent.isEnabled)
        }
    }

}