package cz.airbank.airbankapplication.repository

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Base class for repositories.
 *
 * @author Ivo Chvojka
 */
open class BaseRepository {

    protected fun <T> applyObservableSchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    protected fun <T> applyFlowableSchedulers(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    protected fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer {
            it
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

}