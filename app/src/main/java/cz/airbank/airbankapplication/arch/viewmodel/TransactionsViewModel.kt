package cz.airbank.airbankapplication.arch.viewmodel

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableBoolean
import cz.airbank.airbankapplication.arch.event.SingleLiveEvent
import cz.airbank.airbankapplication.model.Direction
import cz.airbank.airbankapplication.model.Transaction
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
    private val transactionsEvent = SingleLiveEvent<List<Transaction>>()
    private val transactionEvent = SingleLiveEvent<Transaction>()
    private var transactions: MutableList<Transaction> = mutableListOf()

    fun getTransactions(direction: Direction) {
        if (transactions.isEmpty()) {
            with(manager) {
                add(setupSingle(repo.getTransactions(), "Transactions")
                        .doOnSubscribe { progressVisibility.set(true) }
                        .doFinally({ progressVisibility.set(false) })
                        .subscribe({
                            it.items?.let {
                                transactions.addAll(it);
                                transactionsEvent.value = filterTransactions(it, direction)
                            }
                        }, { handleError(it) })
                )
            }
        } else {
            progressVisibility.set(true)
            transactionsEvent.value = filterTransactions(transactions, direction)
            progressVisibility.set(false)
        }
    }

    fun observeTransactions(owner: LifecycleOwner, action: (transitionList: List<Transaction>?) -> Unit) {
        transactionsEvent.observe(owner, Observer { result -> action(result) })
    }

    fun observeTransaction(owner: LifecycleOwner, action: (transition: Transaction?) -> Unit) {
        transactionEvent.observe(owner, Observer { result -> action(result) })
    }

    fun onTransactionClick(transaction: Transaction) {
        transactionEvent.value = transaction
    }

    private fun filterTransactions(allTransactions: List<Transaction>, filter: Direction): List<Transaction> {
        if (filter == Direction.ALL) {
            return allTransactions
        } else {
            return allTransactions.filter { transaction -> transaction.direction == filter }
        }
    }
}