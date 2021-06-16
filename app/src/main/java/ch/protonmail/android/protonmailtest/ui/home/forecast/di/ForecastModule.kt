package ch.protonmail.android.protonmailtest.ui.home.forecast.di

import androidx.lifecycle.ViewModel
import ch.protonmail.android.protonmailtest.ui.home.forecast.ForecastViewModel
import dagger.Binds
import dagger.Module

@Module(includes = [ForecastModule.Bindings::class])
object ForecastModule {

    @Module
    interface Bindings {

        @Binds
        fun bindsViewModel(viewModel: ForecastViewModel): ViewModel
    }
}
