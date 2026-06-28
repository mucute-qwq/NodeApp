package io.github.mucute.qwq.nodedev.mvi

import androidx.lifecycle.ViewModel
import io.github.mucute.qwq.nodedev.application.AppContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.supervisorScope

@Suppress("PropertyName")
abstract class MVIViewModel<S : UIState, I : UIIntent, D : Depository> : ViewModel() {

    val viewModelScope =
        MainScope() + CoroutineName("${javaClass.name}CoroutineScope") + CoroutineExceptionHandler { _, throwable ->
            AppContext.appContextScope.launch {
                onError(throwable)
            }
        }

    protected val _state = MutableStateFlow(initialState())

    protected val _intent: Channel<I> = Channel(Channel.UNLIMITED)

    val state = _state.asStateFlow()

    val intent = _intent

    protected val _depository: D = initialDepository(_state, _intent)

    init {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                supervisorScope {
                    launch {
                        onIntent(it)
                    }
                }
            }
        }
    }

    protected abstract fun initialDepository(state: MutableStateFlow<S>, intent: Channel<I>): D

    protected abstract fun initialState(): S

    protected abstract suspend fun onIntent(intent: I)

    protected open suspend fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

}