package cz.airbank.airbankapplication.arch.viewmodel

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import cz.airbank.airbankapplication.model.TransactionDetail
import cz.airbank.airbankapplication.repository.TransactionRepository
import javax.inject.Inject

/**
 * ViewModel for transactions.
 *
 * @author Ivo Chvojka
 */
class TransactionDetailViewModel @Inject constructor(app: Application, private val repo: TransactionRepository)
    : BaseViewModel(app) {

    val progressVisibility = ObservableBoolean()
    val transitionDetail = ObservableField<TransactionDetail>()

    fun loadTransactionDetail(transactionId: Long) {
        progressVisibility.set(true)
        with(manager) {
            add(setupSingle(repo.getTransitionDetail(transactionId), "TransitionDetail")
                    .doFinally({ progressVisibility.set(false) })
                    .subscribe({ transitionDetail.set(it) }, { handleError(it) })
            )
        }
    }

}