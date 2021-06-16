package ch.protonmail.android.protonmailtest.ui.home.hottest.di

import android.view.View
import ch.protonmail.android.protonmailtest.domain.interactor.GetForecastUseCase
import ch.protonmail.android.protonmailtest.domain.interactor.GetHottestForecastUseCase
import ch.protonmail.android.protonmailtest.ui.home.forecast.di.ForecastModule
import ch.protonmail.android.protonmailtest.ui.home.hottest.HottestFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ForecastModule.Bindings::class,
        HottestModule.Bindings::class,
    ]
)
object HottestModule {

    @Provides
    fun providesImageLoader(fragment: HottestFragment): RequestManager = Glide.with(fragment)

    @Provides
    fun providesScreenView(fragment: HottestFragment): View = fragment.requireView()

    @Module
    interface Bindings {

        @Binds
        fun bindsUseCase(useCase: GetHottestForecastUseCase): GetForecastUseCase
    }
}
