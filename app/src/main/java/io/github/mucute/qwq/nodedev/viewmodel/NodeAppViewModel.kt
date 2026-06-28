package io.github.mucute.qwq.nodedev.viewmodel

import androidx.lifecycle.viewModelScope
import io.github.mucute.qwq.nodedev.depository.NodeAppDepository
import io.github.mucute.qwq.nodedev.mvi.MVIViewModel
import io.github.mucute.qwq.nodedev.mvi.UIIntent
import io.github.mucute.qwq.nodedev.mvi.UIState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

enum class AppThemeMode {
    System, Light, Dark
}

data class NodeAppState(
    val appThemeMode: AppThemeMode = AppThemeMode.System,
    val appThemeMonetColor: Boolean = false
) : UIState

sealed interface NodeAppIntent : UIIntent {

    data class ChangeAppThemeMode(
        val appThemeMode: AppThemeMode,
        val appThemeMonetColor: Boolean
    ) : NodeAppIntent

}

class NodeAppViewModel : MVIViewModel<NodeAppState, NodeAppIntent, NodeAppDepository>() {

    override fun initialDepository(
        state: MutableStateFlow<NodeAppState>,
        intent: Channel<NodeAppIntent>
    ) = NodeAppDepository(state, intent)


    override fun initialState() = NodeAppState()

    init {
        viewModelScope.launch {
            _depository.fetchAndUpdate()
        }
    }

    override suspend fun onIntent(intent: NodeAppIntent) {
        when (intent) {
            is NodeAppIntent.ChangeAppThemeMode -> {
                _depository.changeAppTheme(intent.appThemeMode, intent.appThemeMonetColor)
            }
        }
    }

}