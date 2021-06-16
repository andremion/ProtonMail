package ch.protonmail.android.protonmailtest.ui.home.hottest

import android.os.Bundle
import android.view.View
import ch.protonmail.android.protonmailtest.ui.home.forecast.ForecastFragment
import dagger.android.support.AndroidSupportInjection

/**
 * Created by ProtonMail on 2/25/19.
 * Shows any days that have less than a 50% chance of rain, ordered hottest to coldest
 **/
class HottestFragment : ForecastFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)
    }
}
