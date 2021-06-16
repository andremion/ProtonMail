package ch.protonmail.android.protonmailtest.di

import ch.protonmail.android.protonmailtest.ui.detail.DetailsActivity
import ch.protonmail.android.protonmailtest.ui.detail.di.DetailsModule
import ch.protonmail.android.protonmailtest.ui.home.hottest.HottestFragment
import ch.protonmail.android.protonmailtest.ui.home.hottest.di.HottestModule
import ch.protonmail.android.protonmailtest.ui.home.upcoming.UpcomingFragment
import ch.protonmail.android.protonmailtest.ui.home.upcoming.di.UpcomingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FeaturesModule {

    @ContributesAndroidInjector(modules = [UpcomingModule::class])
    fun bindsUpcomingFragment(): UpcomingFragment

    @ContributesAndroidInjector(modules = [HottestModule::class])
    fun bindsHottestFragment(): HottestFragment

    @ContributesAndroidInjector(modules = [DetailsModule::class])
    fun bindsDetailsActivity(): DetailsActivity
}
