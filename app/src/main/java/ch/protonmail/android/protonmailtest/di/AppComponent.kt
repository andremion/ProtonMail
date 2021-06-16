package ch.protonmail.android.protonmailtest.di

import android.app.Application
import android.content.Context
import ch.protonmail.android.protonmailtest.ProtonApp
import ch.protonmail.android.protonmailtest.data.local.di.LocalModule
import ch.protonmail.android.protonmailtest.data.remote.di.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        LocalModule::class,
        RemoteModule::class,
        FeaturesModule::class,
    ]
)
@Singleton
interface AppComponent : AndroidInjector<ProtonApp> {

    val context: Context

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }
}
