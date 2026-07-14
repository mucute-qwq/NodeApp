package io.github.mucute.qwq.nodedev.shared.application

import android.app.Application
import io.github.mucute.qwq.nodedev.shared.file.initializeFiles
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus

class AppContext : Application() {

    companion object {

        lateinit var instance: AppContext
            private set

        val appContextScope = MainScope() + CoroutineName("AppContextCoroutine") + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeFiles()
    }

    override fun onTerminate() {
        super.onTerminate()
        appContextScope.cancel()
    }

}