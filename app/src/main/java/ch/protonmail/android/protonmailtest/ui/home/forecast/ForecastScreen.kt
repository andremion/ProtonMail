package ch.protonmail.android.protonmailtest.ui.home.forecast

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.ui.util.BaseScreen
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ForecastScreen @Inject constructor(
    private val view: View,
    imageLoader: RequestManager
) : BaseScreen<ForecastViewEvent>() {

    private val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
    private val progressView: View = view.findViewById(R.id.progress)

    private val listAdapter = ForecastListAdapter(imageLoader) { forecast ->
        _event.tryEmit(ForecastViewEvent.ForecastClicked(forecast.day))
    }

    init {
        setupRecyclerView()
    }

    fun render(state: ForecastViewState) {
        when (state) {
            is ForecastViewState.Idle -> {
                progressView.isVisible = false
            }
            is ForecastViewState.Loading -> {
                progressView.isVisible = true
                listAdapter.submitList(emptyList())
            }
            is ForecastViewState.Result -> {
                progressView.isVisible = false
                listAdapter.submitList(state.items)
            }
            is ForecastViewState.EmptyResult -> {
                progressView.isVisible = false
                listAdapter.submitList(emptyList())
                // TODO Empty UI
                showError(RuntimeException("No forecast"))
            }
            is ForecastViewState.Error -> {
                progressView.isVisible = false
                showError(state.error)
            }
        }
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = listAdapter
    }

    private fun showError(error: Throwable) {
        val text = if (error.message.isNullOrBlank()) {
            view.context.getString(R.string.generic_error)
        } else {
            requireNotNull(error.message)
        }
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
    }
}

sealed class ForecastViewEvent {
    data class ForecastClicked(val day: Int) : ForecastViewEvent()
}
