package com.emu.android.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.airbank.airbankapplication.BR
import cz.airbank.airbankapplication.arch.viewmodel.BaseViewModel
import cz.airbank.airbankapplication.arch.viewmodel.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Abstract class for fragments based on ViewModel.
 *
 * @author Ivo Chvojka
 */

abstract class ViewModelBaseFragment<T : BaseViewModel, B : ViewDataBinding> : BaseFragment<B>() {
    abstract val viewModelClazz: Class<T>

    @Inject
    lateinit var factory: ViewModelFactory
    protected val viewModel: T by lazy { ViewModelProviders.of(this, factory).get(viewModelClazz) }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflateBinding(inflater)
        binding.setVariable(BR.view, this)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }


}