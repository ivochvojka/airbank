package cz.airbank.airbankapplication.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Main Dagger module.
 *
 * @author Ivo Chvojka
 */
@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext
}
