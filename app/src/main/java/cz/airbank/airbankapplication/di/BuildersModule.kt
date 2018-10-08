package cz.airbank.airbankapplication.di

import cz.airbank.airbankapplication.ui.transaction.TransactionDetailActivity
import cz.airbank.airbankapplication.ui.transaction.TransactionDetailFragment
import cz.airbank.airbankapplication.ui.transaction.TransactionsActivity
import cz.airbank.airbankapplication.ui.transaction.TransactionsFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

/**
 * Contributes module.
 *
 * @author Ivo Chvojka
 */

@Module(includes = [(AndroidInjectionModule::class)])
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindTransactionsActivity(): TransactionsActivity

    @ContributesAndroidInjector
    abstract fun bindTransactionDetailActivity(): TransactionDetailActivity

    @ContributesAndroidInjector
    abstract fun bindTransactionsFragment(): TransactionsFragment

    @ContributesAndroidInjector
    abstract fun bindTransactionDetailFragment(): TransactionDetailFragment
}