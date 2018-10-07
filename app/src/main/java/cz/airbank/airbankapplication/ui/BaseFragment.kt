package com.emu.android.ui

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.airbank.airbankapplication.BR

/**
 * Base class for fragments.
 *
 * @author Ivo Chvojka
 */

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {

    protected lateinit var binding: B

    abstract fun inflateBinding(inflater: LayoutInflater): B

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.executePendingBindings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflateBinding(inflater)
        binding.setVariable(BR.view, this)
        return binding.root
    }
}