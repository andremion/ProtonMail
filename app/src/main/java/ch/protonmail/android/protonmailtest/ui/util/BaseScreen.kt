package ch.protonmail.android.protonmailtest.ui.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseScreen<Event> {

    protected val _event: MutableSharedFlow<Event> = MutableSharedFlow(
        // We need a buffer to temporarily store values when using tryEmit() because it is not a suspend function
        // https://blog.danlew.net/2021/03/23/do-or-do-not-there-is-no-tryemit/
        extraBufferCapacity = 1
    )
    val event: SharedFlow<Event> = _event
}
