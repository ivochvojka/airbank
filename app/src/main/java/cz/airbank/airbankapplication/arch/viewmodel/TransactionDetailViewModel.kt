package cz.airbank.airbankapplication.arch.viewmodel

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import cz.airbank.airbankapplication.model.Transaction
import cz.airbank.airbankapplication.model.TransactionDetail
import cz.airbank.airbankapplication.model.TransactionDetailWrapper
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
    val transaction = ObservableField<Transaction>()
    val transactionDetail = ObservableField<TransactionDetail>()

    fun loadTransactionDetail(transaction: Transaction) {
        this.transaction.set(transaction)
        progressVisibility.set(true)
        with(manager) {
            add(setupSingle(repo.getTransitionDetail(transaction.id), "TransitionDetail")
                    .doFinally { progressVisibility.set(false) }
                    .subscribe({ transactionDetail.set(it) }, { handleError(it) })
            )
        }
    }

}