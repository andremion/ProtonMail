package ch.protonmail.android.protonmailtest.ui.detail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.ui.detail.model.ForecastDetailsModel
import ch.protonmail.android.protonmailtest.ui.util.BaseScreen
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DetailsScreen @Inject constructor(
    private val view: View,
    private val imageLoader: RequestManager
) : BaseScreen<DetailsViewEvent>() {

    private val toolbar: Toolbar = view.findViewById(R.id.toolbar)
    private val imageView: ImageView = view.findViewById(R.id.image)
    private val descriptionView: TextView = view.findViewById(R.id.description)
    private val chanceOfRainView: TextView = view.findViewById(R.id.chance_rain)
    private val downloadButton: View = view.findViewById(R.id.downloadButton)

    fun render(state: DetailsViewState) {
        when (state) {
            is DetailsViewState.Idle -> {
                downloadButton.isVisible = false
            }
            is DetailsViewState.Result -> {
                onResultState(state.model)
            }
            is DetailsViewState.Error -> {
                showError(state.error)
            }
        }
    }

    private fun onResultState(model: ForecastDetailsModel) {
        toolbar.title = model.title
        imageLoader.load(model.imageFile)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
        descriptionView.text = model.description
        descriptionView.text = model.description
        chanceOfRainView.text = model.chanceOfRain
        downloadButton.isVisible = model.showDownloadButton
        downloadButton.setOnClickListener {
            _event.tryEmit(DetailsViewEvent.DownloadClicked(model.day, model.imageUri))
        }
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

sealed class DetailsViewEvent {
    data class DownloadClicked(val day: Int, val image: String) : DetailsViewEvent()
}
