package cz.airbank.airbankapplication.di

import android.app.Application
import cz.airbank.airbankapplication.AirbankApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton


/**
 * Component for Dagger.
 *
 * @author Ivo Chvojka
 */

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (RemoteModule::class), (BuildersModule::class)])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: AirbankApplication)

}
