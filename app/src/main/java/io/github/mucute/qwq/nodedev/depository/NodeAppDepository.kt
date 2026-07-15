package io.github.mucute.qwq.nodedev.depository

import android.content.Context
import android.util.Log
import androidx.core.content.edit
import de.jonasbroeckmann.kzip.Zip
import de.jonasbroeckmann.kzip.extractTo
import de.jonasbroeckmann.kzip.open
import io.github.mucute.qwq.nodedev.model.AppLanguage
import io.github.mucute.qwq.nodedev.model.AppThemeMode
import io.github.mucute.qwq.nodedev.model.Project
import io.github.mucute.qwq.nodedev.model.ProjectTemplate
import io.github.mucute.qwq.nodedev.shared.application.AppContext
import io.github.mucute.qwq.nodedev.shared.file.ProjectFolder
import io.github.mucute.qwq.nodedev.shared.mvi.Depository
import io.github.mucute.qwq.nodedev.shared.util.JsonDefault
import io.github.mucute.qwq.nodedev.shared.util.JsonFormatted
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppIntent
import io.github.mucute.qwq.nodedev.viewmodel.NodeAppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import kotlinx.io.files.Path
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import java.io.File
import java.util.Locale.getDefault

class NodeAppDepository(state: MutableStateFlow<NodeAppState>, intent: Channel<NodeAppIntent>) :
    Depository<NodeAppState, NodeAppIntent>(state, intent) {

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

    suspend fun newProject(name: String, packageName: String, template: ProjectTemplate) =
        withContext(Dispatchers.IO) {
            val projectFolder = File(ProjectFolder, name)
            if (projectFolder.exists()) return@withContext

            projectFolder.mkdirs()

            val workspaceFolder = File(projectFolder, ".workspace")
            workspaceFolder.mkdirs()

            val project = Project(
                name = name,
                packageName = packageName,
                openedFiles = emptyList(),
                pinned = false
            )

            val projectFile = File(workspaceFolder, "project.json")
            projectFile.writeText(JsonFormatted.encodeToString(project))

            val templateCacheFile = File(AppContext.instance.cacheDir, "${template.name}.zip")
            AppContext.instance.assets.open(template.path).use { inputStream ->
                templateCacheFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }

            Zip.open(Path(templateCacheFile.absolutePath))
                .extractTo(Path(projectFolder.absolutePath))
            templateCacheFile.delete()

            val packageJsonFile = File(projectFolder, "package.json")
            val jsonObject = buildJsonObject {
                val originalJsonObject =
                    JsonDefault.parseToJsonElement(packageJsonFile.readText()).jsonObject
                originalJsonObject.forEach {
                    put(it.key, it.value)
                }

                put("name", JsonPrimitive(name.lowercase(getDefault())))
            }
            packageJsonFile.writeText(JsonFormatted.encodeToString(jsonObject))

            refreshProjects()
        }

    suspend fun refreshProjects() = withContext(Dispatchers.IO) {
        val projects = buildList {
            val rootProjectFolders = ProjectFolder.listFiles() ?: emptyArray()
            for (rootProjectFolder in rootProjectFolders) {
                val workspaceFolder = File(rootProjectFolder, ".workspace")
                if (!workspaceFolder.exists() || workspaceFolder.isFile) {
                    continue
                }

                val projectFile = File(workspaceFolder, "project.json")
                val project: Project = JsonDefault.decodeFromString(projectFile.readText())
                add(project)
            }
        }.sortedBy { it.name }

        state.update {
            it.copy(projects = projects)
        }
    }

    suspend fun deleteProject(project: Project) = withContext(Dispatchers.IO) {
        Log.e("CurrentProject", project.name)
        val projectFolder = project.rootProjectFolder
        if (!projectFolder.exists()) return@withContext
        projectFolder.deleteRecursively()

        refreshProjects()
    }

    suspend fun pinOrUnpinProject(project: Project) = withContext(Dispatchers.IO) {
        val modifiedProject = project.copy(pinned = !project.pinned)
        val projectFile = project.projectFile
        if (!projectFile.exists() || projectFile.isDirectory) return@withContext
        projectFile.writeText(JsonFormatted.encodeToString(modifiedProject))

        refreshProjects()
    }

    suspend fun renameProject(project: Project) = withContext(Dispatchers.IO) {

    }

}