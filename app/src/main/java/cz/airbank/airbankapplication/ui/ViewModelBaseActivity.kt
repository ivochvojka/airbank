package cz.airbank.airbankapplication.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import cz.airbank.airbankapplication.arch.viewmodel.BaseViewModel
import cz.airbank.airbankapplication.arch.viewmodel.factory.ViewModelFactory
import javax.inject.Inject

/**
 * Abstract class for activities based on ViewModel.
 *
 * @author Ivo Chvojka
 */

abstract class ViewModelBaseActivity<T : BaseViewModel> : BaseActivity() {
    abstract val viewModelClazz: Class<T>

    @Inject
    lateinit var factory: ViewModelFactory
    protected val viewModel: T by lazy { ViewModelProviders.of(this, factory).get(viewModelClazz) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }
}