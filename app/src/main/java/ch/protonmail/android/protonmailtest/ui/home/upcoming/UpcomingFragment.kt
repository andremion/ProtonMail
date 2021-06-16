package ch.protonmail.android.protonmailtest.ui.home.upcoming

import android.os.Bundle
import android.view.View
import ch.protonmail.android.protonmailtest.ui.home.forecast.ForecastFragment
import dagger.android.support.AndroidSupportInjection

/**
 * Created by ProtonMail on 2/25/19.
 * Shows the upcoming list of days returned by the API in order of day
 **/
class UpcomingFragment : ForecastFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)
    }
}
