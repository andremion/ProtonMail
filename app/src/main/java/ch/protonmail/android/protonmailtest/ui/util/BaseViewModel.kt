package ch.protonmail.android.protonmailtest.ui.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State>(initial: State) : ViewModel() {

    protected val _state: MutableStateFlow<State> = MutableStateFlow(initial)
    val state: StateFlow<State> = _state
}
