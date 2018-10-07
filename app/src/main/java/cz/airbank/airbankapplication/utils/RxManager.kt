package cz.airbank.airbankapplication.utils

import cz.airbank.airbankapplication.remote.RestHttpException
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import timber.log.Timber

/**
 * Manager for Rx calls.
 *
 * @author Ivo Chvojka
 */

open class RxManager {

    private val disposables = CompositeDisposable()
    private val runningCalls = mutableMapOf<String, Short>()

    fun <T> setupFlowable(flowable: Flowable<T>, callType: String): Flowable<T> {
        return flowable
                .doOnSubscribe { addRunningCall(callType) }
                .doOnNext { logSuccess(it, callType) }
                .doFinally { removeRunningCall(callType) }
                .onErrorResumeNext { error: Throwable ->
                    when (error) {
                        is HttpException -> Flowable.error(RestHttpException(error.response()))
                        else -> Flowable.error(error)
                    }
                }
                .doOnError { logError(it, callType) }
    }

    fun <T> setupObservable(observable: Observable<T>, callType: String): Observable<T> {
        return observable
                .doOnSubscribe { addRunningCall(callType) }
                .doOnNext { logSuccess(it, callType) }
                .doFinally { removeRunningCall(callType) }
                .onErrorResumeNext { error: Throwable ->
                    when (error) {
                        is HttpException -> Observable.error(RestHttpException(error.response()))
                        else -> Observable.error(error)
                    }
                }
                .doOnError { logError(it, callType) }
    }

    fun <T> setupSingle(single: Single<T>, callType: String): Single<T> {
        return single
                .doOnSubscribe { addRunningCall(callType) }
                .doOnSuccess { logSuccess(it, callType) }
                .doFinally { removeRunningCall(callType) }
                .onErrorResumeNext {
                    when (it) {
                        is HttpException -> Single.error(RestHttpException(it.response()))
                        else -> Single.error(it)
                    }
                }
                .doOnError { logError(it, callType) }
    }

    fun <T> setupMaybe(maybe: Maybe<T>, callType: String): Maybe<T> {
        return maybe
                .doOnSubscribe { addRunningCall(callType) }
                .doOnSuccess { logSuccess(it, callType) }
                .doFinally { removeRunningCall(callType) }
                .onErrorResumeNext { error: Throwable ->
                    when (error) {
                        is HttpException -> Maybe.error(RestHttpException(error.response()))
                        else -> Maybe.error(error)
                    }
                }
                .doOnError { logError(it, callType) }
    }

    open fun setupCompletable(completable: Completable, callType: String): Completable {
        return completable
                .doOnSubscribe { addRunningCall(callType);disposables.add(it) }
                .doOnComplete { logSuccess(callType) }
                .doFinally { removeRunningCall(callType) }
                .onErrorResumeNext {
                    when (it) {
                        is HttpException -> Completable.error(RestHttpException(it.response()))
                        else -> Completable.error(it)
                    }
                }
                .doOnError { logError(it, callType) }
    }

    @Synchronized
    private fun addRunningCall(callType: String) {
        val count = runningCalls[callType] ?: 0+1
        runningCalls[callType] = count
    }

    @Synchronized
    private fun removeRunningCall(callType: String) {
        var count = runningCalls[callType] ?: return

        if (count > 1) runningCalls[callType] = --count
        else runningCalls.remove(callType)
    }

    fun isRunning(callType: String) = runningCalls.containsKey(callType)

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose(disposable: Disposable) {
        disposables.remove(disposable)
    }

    fun disposeAll() {
        disposables.clear()
        runningCalls.clear()
    }

    /**
     * ------------------
     * Logging all events
     * ------------------
     */

    private fun logSuccess(callType: String) {
        Timber.d("$callType call succeed")
    }

    private fun <T> logSuccess(event: T, callType: String) {
        Timber.d("$callType call succeed ${event.toString()}")
    }

    protected open fun logError(t: Throwable, callType: String) {
        when (t) {
            is RestHttpException -> Timber.e("$callType call restError with ${t.code()} ${t.message()}: ${t.responseError}")
            is HttpException -> Timber.e("$callType call restError with ${t.code()} ${t.message()}")
            else -> Timber.e("$callType call error with ${t::class.java}: ${t.message}")
        }
    }

}