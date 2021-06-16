package ch.protonmail.android.protonmailtest.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import ch.protonmail.android.protonmailtest.data.worker.DownloadImageWorker
import ch.protonmail.android.protonmailtest.data.worker.getWorkInfo
import ch.protonmail.android.protonmailtest.data.worker.imageFile
import ch.protonmail.android.protonmailtest.domain.interactor.GetForecastDetailsUseCase
import ch.protonmail.android.protonmailtest.ui.detail.mapper.ForecastDetailsModelMapper
import ch.protonmail.android.protonmailtest.ui.detail.model.ForecastDetailsModel
import ch.protonmail.android.protonmailtest.ui.util.BaseAndroidViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    application: Application,
    private val getForecastDetails: GetForecastDetailsUseCase,
    private val detailsModelMapper: ForecastDetailsModelMapper
) : BaseAndroidViewModel<DetailsViewState>(application, DetailsViewState.Idle) {

    private val workManager: WorkManager = WorkManager.getInstance(application)

    fun init(id: Int): LiveData<List<WorkInfo>> {
        viewModelScope.launch {
            _state.value = try {
                val forecast = getForecastDetails(id)
                val model = detailsModelMapper.map(forecast)
                DetailsViewState.Result(model)
            } catch (e: Throwable) {
                DetailsViewState.Error(e)
            }
        }
        return workManager.getWorkInfo(id)
    }

    fun onWorkInfo(workInfos: List<WorkInfo>) {
        if (workInfos.isEmpty()) return
        val workInfo = workInfos[0]
        _state.value = when (workInfo.state) {
            WorkInfo.State.ENQUEUED,
            WorkInfo.State.RUNNING,
            WorkInfo.State.BLOCKED -> DetailsViewState.DownloadInProgress
            WorkInfo.State.SUCCEEDED -> DetailsViewState.DownloadCompleted(workInfo.imageFile)
            WorkInfo.State.FAILED,
            WorkInfo.State.CANCELLED -> DetailsViewState.DownloadFailed
        }
    }

    fun onDownloadClicked(day: Int, image: String) {
        val workRequest = DownloadImageWorker.createWorkRequest(id = day, uri = image)
        workManager.enqueue(workRequest)
    }
}

sealed class DetailsViewState {
    object Idle : DetailsViewState()
    data class Result(val model: ForecastDetailsModel) : DetailsViewState()
    data class Error(val error: Throwable) : DetailsViewState()
    object DownloadInProgress : DetailsViewState()
    data class DownloadCompleted(val imageFile: File) : DetailsViewState()
    object DownloadFailed : DetailsViewState()
}
