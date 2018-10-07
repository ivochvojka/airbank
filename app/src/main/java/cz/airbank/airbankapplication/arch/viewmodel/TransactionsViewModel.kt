package cz.airbank.airbankapplication.arch.viewmodel

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import cz.airbank.airbankapplication.arch.event.SingleLiveEvent
import cz.airbank.airbankapplication.model.Transaction
import cz.airbank.airbankapplication.model.TransactionDetail
import cz.airbank.airbankapplication.repository.TransactionRepository
import javax.inject.Inject

/**
 * ViewModel for transactions.
 *
 * @author Ivo Chvojka
 */
class TransactionsViewModel @Inject constructor(app: Application, private val repo: TransactionRepository)
    : BaseViewModel(app) {

    val progressVisibility = ObservableBoolean()
    val transitionDetail = ObservableField<TransactionDetail>()
    private val transactionsEvent = SingleLiveEvent<List<Transaction>>()

    override fun onCreate() {
        super.onCreate()

        progressVisibility.set(true)
        with(manager) {
            add(setupFlowable(repo.getTransitions(), "Transitions")
                    .doFinally({ progressVisibility.set(false) })
                    .subscribe({ transactionsEvent.value = it }, { handleError(it) })
            )
        }
    }

    fun observeTransactions(owner: LifecycleOwner, action: (transitionList: List<Transaction>?) -> Unit) {
        transactionsEvent.observe(owner, Observer { result -> action(result) })
    }

    fun getTransactionDetail(transactionId: Long) {
        progressVisibility.set(true)
        with(manager) {
            add(setupSingle(repo.getTransitionDetail(transactionId), "TransitionDetail")
                    .doFinally({ progressVisibility.set(false) })
                    .subscribe({ transitionDetail.set(it) }, { handleError(it) })
            )
        }
    }
}