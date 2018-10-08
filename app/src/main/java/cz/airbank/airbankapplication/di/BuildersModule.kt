package cz.airbank.airbankapplication.di

import cz.airbank.airbankapplication.ui.transaction.TransactionDetailActivity
import cz.airbank.airbankapplication.ui.transaction.TransactionDetailFragment
import cz.airbank.airbankapplication.ui.transaction.TransactionListActivity
import cz.airbank.airbankapplication.ui.transaction.TransactionListFragment
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
    abstract fun bindTransactionsActivity(): TransactionListActivity

    @ContributesAndroidInjector
    abstract fun bindTransactionDetailActivity(): TransactionDetailActivity

    @ContributesAndroidInjector
    abstract fun bindTransactionsFragment(): TransactionListFragment

    @ContributesAndroidInjector
    abstract fun bindTransactionDetailFragment(): TransactionDetailFragment
}