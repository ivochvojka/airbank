package cz.airbank.airbankapplication

import com.facebook.stetho.Stetho
import cz.airbank.airbankapplication.di.DaggerAppComponent
import cz.airbank.airbankapplication.utils.NotLoggingTree
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber


/**
 * Airbank application.
 *
 * @author Ivo Chvojka
 */
class AirbankApplication : DaggerApplication() {

    companion object {
        lateinit var instance: AirbankApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree());
        else
            Timber.plant(NotLoggingTree());

        Stetho.initializeWithDefaults(this);
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }
}