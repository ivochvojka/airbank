package cz.airbank.airbankapplication.repository

import cz.airbank.airbankapplication.model.Transaction
import cz.airbank.airbankapplication.model.TransactionDetail
import cz.airbank.airbankapplication.remote.RemoteService
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for getTransactions.
 *
 * @author Ivo Chvojka
 */
@Singleton
class TransactionRepository @Inject constructor(private val service: RemoteService) : BaseRepository() {

    fun getTransitions(): Flowable<List<Transaction>> {
        return service.getTransactions()
                .compose(applyFlowableSchedulers())
    }

    fun getTransitionDetail(transactionId: Long): Single<TransactionDetail> {
        return service.getTransactionDetail(transactionId)
                .compose(applySingleSchedulers())
    }


}