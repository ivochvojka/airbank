package cz.airbank.airbankapplication.di

//import com.readystatesoftware.chuck.ChuckInterceptor
import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import cz.airbank.airbankapplication.BuildConfig
import cz.airbank.airbankapplication.remote.RemoteService
import cz.airbank.airbankapplication.remote.ServiceInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Module for network communication.
 *
 * @author Ivo Chvojka
 */

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideRemoteService(client: OkHttpClient, @Named("gson") gson: Converter.Factory): RemoteService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.URL)
                .client(client)
                .addConverterFactory(gson)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RemoteService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor, authenticator: Authenticator): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            val loggerInterceptor = HttpLoggingInterceptor { Timber.tag("OkHttp").d(it) }
                    .apply { level = HttpLoggingInterceptor.Level.BASIC }

            OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .authenticator(authenticator)
                    .addNetworkInterceptor(StethoInterceptor())
                    .addNetworkInterceptor(loggerInterceptor)
                    .build()
        } else {
            OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()
        }
    }

    @Singleton
    @Provides
    fun provideServiceInterceptor(context: Context): Interceptor = ServiceInterceptor(context)

    @Singleton
    @Provides
    @Named("gson")
    fun createGsonFactory(): Converter.Factory {
        val gson = GsonBuilder()
                .create()
        return GsonConverterFactory.create(gson)
    }
}