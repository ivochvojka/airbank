package cz.airbank.airbankapplication.arch.event

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.annotation.StringRes

/**
 * SingleLiveEvent for snackbars.
 *
 * @author Ivo Chvojka
 */

class SnackbarLiveEvent : SingleLiveEvent<Int>() {

    fun observe(owner: LifecycleOwner, observer: SnackbarObserver) {
        super.observe(owner, Observer { t ->
            observer.onNewSnackbarMessage(t)
        })
    }

    interface SnackbarObserver {
        fun onNewSnackbarMessage(@StringRes messageResourceId: Int?)
    }
}