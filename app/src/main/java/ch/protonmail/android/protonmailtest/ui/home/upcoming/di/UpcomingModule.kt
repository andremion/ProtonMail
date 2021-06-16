package ch.protonmail.android.protonmailtest.ui.home.upcoming.di

import android.view.View
import ch.protonmail.android.protonmailtest.domain.interactor.GetForecastUseCase
import ch.protonmail.android.protonmailtest.domain.interactor.GetUpcomingForecastUseCase
import ch.protonmail.android.protonmailtest.ui.home.forecast.di.ForecastModule
import ch.protonmail.android.protonmailtest.ui.home.upcoming.UpcomingFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ForecastModule.Bindings::class,
        UpcomingModule.Bindings::class
    ]
)
object UpcomingModule {

    @Provides
    fun providesImageLoader(fragment: UpcomingFragment): RequestManager = Glide.with(fragment)

    @Provides
    fun providesScreenView(fragment: UpcomingFragment): View = fragment.requireView()

    @Module
    interface Bindings {

        @Binds
        fun bindsUseCase(useCase: GetUpcomingForecastUseCase): GetForecastUseCase
    }
}
