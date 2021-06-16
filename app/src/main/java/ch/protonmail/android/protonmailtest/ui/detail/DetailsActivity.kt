package ch.protonmail.android.protonmailtest.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.di.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by ProtonMail on 2/25/19.
 * Shows all the details for a particular day.
 */
class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID: String = "extra_id"
    }

    private val id: Int get() = intent.getIntExtra(EXTRA_ID, 0)

    @Inject
    lateinit var screen: DetailsScreen

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        AndroidInjection.inject(this)
        viewModel.state
            .onEach(screen::render)
            .launchIn(lifecycleScope)
        screen.event
            .onEach(::onEvent)
            .launchIn(lifecycleScope)
        viewModel.init(id)
            .observe(this, viewModel::onWorkInfo)
    }

    private fun onEvent(event: DetailsViewEvent) {
        when (event) {
            is DetailsViewEvent.DownloadClicked -> {
                viewModel.onDownloadClicked(event.day, event.image)
            }
        }
    }
}
