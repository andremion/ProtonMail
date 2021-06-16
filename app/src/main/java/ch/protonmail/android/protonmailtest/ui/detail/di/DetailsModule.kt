package ch.protonmail.android.protonmailtest.ui.detail.di

import android.view.View
import androidx.lifecycle.ViewModel
import ch.protonmail.android.protonmailtest.ui.detail.DetailsActivity
import ch.protonmail.android.protonmailtest.ui.detail.DetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DetailsModule.Bindings::class])
object DetailsModule {

    @Provides
    fun providesImageLoader(activity: DetailsActivity): RequestManager = Glide.with(activity)

    @Provides
    fun providesScreenView(activity: DetailsActivity): View = activity.findViewById(android.R.id.content)

    @Module
    interface Bindings {

        @Binds
        fun bindsViewModel(viewModel: DetailsViewModel): ViewModel
    }
}
