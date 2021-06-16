package ch.protonmail.android.protonmailtest.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.ui.home.hottest.HottestFragment
import ch.protonmail.android.protonmailtest.ui.home.upcoming.UpcomingFragment

/**
 * Created by ProtonMail on 2/25/19. */
class TabsAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private val tabs: List<Tab> =
        listOf(
            Tab(title = R.string.home_forecast_tab_upcoming, fragment = { UpcomingFragment() }),
            Tab(title = R.string.home_forecast_tab_hottest, fragment = { HottestFragment() })
        )

    override fun getPageTitle(position: Int): CharSequence =
        context.getString(tabs[position].title)

    override fun getItem(position: Int): Fragment =
        tabs[position].fragment()

    override fun getCount(): Int =
        tabs.size
}

private data class Tab(@StringRes val title: Int, val fragment: () -> Fragment)
