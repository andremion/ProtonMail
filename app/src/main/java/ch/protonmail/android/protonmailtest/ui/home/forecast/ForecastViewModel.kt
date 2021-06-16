package ch.protonmail.android.protonmailtest.ui.home.forecast

import androidx.lifecycle.viewModelScope
import ch.protonmail.android.protonmailtest.domain.interactor.GetForecastUseCase
import ch.protonmail.android.protonmailtest.ui.home.forecast.mapper.ForecastItemModelMapper
import ch.protonmail.android.protonmailtest.ui.home.forecast.model.ForecastItemModel
import ch.protonmail.android.protonmailtest.ui.util.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val getForecast: GetForecastUseCase,
    private val itemModelMapper: ForecastItemModelMapper
) : BaseViewModel<ForecastViewState>(ForecastViewState.Idle) {

    fun init() {
        _state.value = ForecastViewState.Loading
        viewModelScope.launch {
            _state.value = try {
                val forecasts = getForecast()
                if (forecasts.isEmpty()) {
                    ForecastViewState.EmptyResult
                } else {
                    val models = itemModelMapper.map(forecasts)
                    ForecastViewState.Result(models)
                }
            } catch (e: Throwable) {
                ForecastViewState.Error(e)
            }
        }
    }
}

sealed class ForecastViewState {
    object Idle : ForecastViewState()
    object Loading : ForecastViewState()
    data class Result(val items: List<ForecastItemModel>) : ForecastViewState()
    object EmptyResult : ForecastViewState()
    data class Error(val error: Throwable) : ForecastViewState()
}
