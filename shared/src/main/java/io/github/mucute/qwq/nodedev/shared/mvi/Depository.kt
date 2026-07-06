package io.github.mucute.qwq.nodedev.shared.mvi

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class Depository<S : UIState, I : UIIntent>(
    val state: MutableStateFlow<S>,
    val intent: Channel<I>
)