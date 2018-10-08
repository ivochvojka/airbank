package cz.airbank.airbankapplication.arch.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.support.annotation.StringRes
import cz.airbank.airbankapplication.R
import cz.airbank.airbankapplication.arch.event.SnackbarLiveEvent
import cz.airbank.airbankapplication.utils.RxManager
import java.io.IOException


/**
 * Base class for ViewModels.
 *
 * @author Ivo Chvojka
 */

abstract class BaseViewModel(app: Application) : AndroidViewModel(app), LifecycleObserver {

    private val managerDelegate = lazy { RxManager() }
    private val snackEvent by lazy { SnackbarLiveEvent() }

    protected val manager by lazy { RxManager() }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }

    override fun onCleared() {
        with(managerDelegate) {
            if (isInitialized()) manager.disposeAll()
        }

        super.onCleared()
    }

    fun sendSnackEvent(@StringRes messageResId: Int) {
        snackEvent.value = messageResId
    }

    fun observeSnackEvent(owner: LifecycleOwner, action: (messageResourceId: Int?) -> Unit) {
        snackEvent.observe(owner, object : SnackbarLiveEvent.SnackbarObserver {
            override fun onNewSnackbarMessage(messageResourceId: Int?) {
                action(messageResourceId)
            }
        })
    }

    protected open fun handleError(throwable: Throwable) {
        when (throwable) {
            is IOException -> sendSnackEvent(R.string.error_connection)
            else -> sendSnackEvent(R.string.error_default)
        }
    }

}