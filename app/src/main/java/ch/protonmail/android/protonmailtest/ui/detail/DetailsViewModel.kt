package ch.protonmail.android.protonmailtest.ui.detail

import androidx.lifecycle.viewModelScope
import ch.protonmail.android.protonmailtest.domain.interactor.GetForecastDetailsUseCase
import ch.protonmail.android.protonmailtest.ui.detail.mapper.ForecastDetailsModelMapper
import ch.protonmail.android.protonmailtest.ui.detail.model.ForecastDetailsModel
import ch.protonmail.android.protonmailtest.ui.util.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getForecastDetails: GetForecastDetailsUseCase,
    private val detailsModelMapper: ForecastDetailsModelMapper
) : BaseViewModel<DetailsViewState>(DetailsViewState.Idle) {

    fun init(day: Int) {
        viewModelScope.launch {
            _state.value = try {
                val forecast = getForecastDetails(day)
                val model = detailsModelMapper.map(forecast)
                DetailsViewState.Result(model)
            } catch (e: Throwable) {
                DetailsViewState.Error(e)
            }
        }
    }
}

sealed class DetailsViewState {
    object Idle : DetailsViewState()
    data class Result(val model: ForecastDetailsModel) : DetailsViewState()
    data class Error(val error: Throwable) : DetailsViewState()
}
