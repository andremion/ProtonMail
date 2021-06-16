package ch.protonmail.android.protonmailtest.ui.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseAndroidViewModel<State>(
    application: Application,
    initial: State
) : AndroidViewModel(application) {

    protected val _state: MutableStateFlow<State> = MutableStateFlow(initial)
    val state: StateFlow<State> = _state
}
