package cz.airbank.airbankapplication.di

import android.arch.lifecycle.ViewModel
import cz.airbank.airbankapplication.arch.viewmodel.TransactionDetailViewModel
import cz.airbank.airbankapplication.arch.viewmodel.TransactionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module for ViewModels.
 *
 * @author Ivo Chvojka
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    abstract fun bindTransitionsViewModel(viewModel: TransactionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionDetailViewModel::class)
    abstract fun bindTransitionDetailViewModel(viewModel: TransactionDetailViewModel): ViewModel

}