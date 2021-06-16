package ch.protonmail.android.protonmailtest.ui.home.forecast

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.di.ViewModelFactory
import ch.protonmail.android.protonmailtest.ui.detail.DetailsActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

abstract class ForecastFragment : Fragment() {

    @Inject
    lateinit var screen: ForecastScreen

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ForecastViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_forecast, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state
            .onEach(screen::render)
            .launchIn(viewLifecycleOwner.lifecycleScope)
        screen.event
            .onEach(::onEvent)
            .launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.init()
    }

    private fun onEvent(event: ForecastViewEvent) {
        when (event) {
            is ForecastViewEvent.ForecastClicked -> {
                Intent(requireContext(), DetailsActivity::class.java)
                    .putExtra(DetailsActivity.EXTRA_DAY, event.day)
                    .let(::startActivity)
            }
        }
    }
}
