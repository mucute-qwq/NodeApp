package io.github.mucute.qwq.nodedev.viewmodel

import io.github.mucute.qwq.nodedev.depository.NodeAppDepository
import io.github.mucute.qwq.nodedev.model.AppLanguage
import io.github.mucute.qwq.nodedev.model.AppThemeMode
import io.github.mucute.qwq.nodedev.model.Project
import io.github.mucute.qwq.nodedev.model.ProjectTemplate
import io.github.mucute.qwq.nodedev.shared.mvi.MVIViewModel
import io.github.mucute.qwq.nodedev.shared.mvi.UIIntent
import io.github.mucute.qwq.nodedev.shared.mvi.UIState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking


data class NodeAppState(
    val appThemeMode: AppThemeMode = AppThemeMode.System,
    val appThemeMonetColor: Boolean = false,
    val appLanguage: AppLanguage = AppLanguage.English,
    val skipGuide: Boolean = false,
    val projects: List<Project> = emptyList()
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

    data class NewProject(
        val name: String,
        val packageName: String,
        val template: ProjectTemplate
    ) : NodeAppIntent

    data object RefreshProjects : NodeAppIntent

    data class PinOrUnpinProject(
        val project: Project
    ) : NodeAppIntent

    data class RenameProject(
        val project: Project
    ) : NodeAppIntent

    data class DeleteProject(
        val project: Project
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

            is NodeAppIntent.NewProject -> _depository.newProject(intent.name, intent.packageName, intent.template)

            NodeAppIntent.RefreshProjects -> _depository.refreshProjects()

            is NodeAppIntent.DeleteProject -> _depository.deleteProject(intent.project)

            is NodeAppIntent.PinOrUnpinProject -> _depository.pinOrUnpinProject(intent.project)

            is NodeAppIntent.RenameProject -> _depository.renameProject(intent.project)
        }
    }

}