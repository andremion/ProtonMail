package ch.protonmail.android.protonmailtest.data.remote.di

import ch.protonmail.android.protonmailtest.BuildConfig
import ch.protonmail.android.protonmailtest.data.remote.ForecastService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

private const val FORECAST_API_URL = "https://5c5c8ba58d018a0014aa1b24.mockapi.io/"

@Module
object RemoteModule {

    @Provides
    @Singleton
    fun providesForecastService(retrofitBuilder: Retrofit.Builder): ForecastService =
        retrofitBuilder
            .baseUrl(FORECAST_API_URL)
            .build()
            .create(ForecastService::class.java)

    @Provides
    fun providesRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
    }

    @Provides
    fun providesOkHttpClient(level: HttpLoggingInterceptor.Level): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(level)
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun providesLoggingLevel(): HttpLoggingInterceptor.Level =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
}
