package cz.airbank.airbankapplication.di

import dagger.Module
import dagger.android.AndroidInjectionModule

/**
 * Contributes module.
 * FIXME delete this
 * @author Ivo Chvojka
 */

@Module(includes = [(AndroidInjectionModule::class)])
abstract class BuildersModule {

//    @ContributesAndroidInjector
//    abstract fun bindTransactionActivity(): TransactionsActivity

}