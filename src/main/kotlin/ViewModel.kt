package cholwell.dev.android_mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Model View Intent [ViewModel]
 */
abstract class ViewModel<I : Input, O : Output, S : State, E : Effect>(
    initialState: S,
    private val reducer: Reducer<O, S>
) : ViewModel() {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effects: MutableSharedFlow<E> = MutableSharedFlow()
    val effects: Flow<E> = _effects

    fun input(input: I) {
        log("Input", input)
        viewModelScope.launch {
            resolve(input, _state.value).onEach { result ->
                when (result) {
                    is Effect -> {
                        log("Effect", result)
                        _effects.emit(result as E)
                    }

                    else -> {
                        _state.update { state ->
                            log("Result", result)
                            reducer(result as O, state).also { log("State", it) }
                        }
                    }
                }
            }
                .launchIn(this)
        }
    }

    abstract suspend fun resolve(input: I, state: S): Flow<Output>

    private val tag = this::class.simpleName ?: "ViewModel"

    private fun log(type: String, item: Any) = Log.d(tag, "[$type : $item]")
}