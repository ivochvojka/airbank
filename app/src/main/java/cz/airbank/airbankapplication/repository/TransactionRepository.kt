package cz.airbank.airbankapplication.repository

import cz.airbank.airbankapplication.model.TransactionDetail
import cz.airbank.airbankapplication.model.Transactions
import cz.airbank.airbankapplication.remote.RemoteService
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

    fun getTransactions(): Single<Transactions> {
        return service.getTransactions()
                .compose(applySingleSchedulers())
    }

    fun getTransitionDetail(transactionId: Long): Single<TransactionDetail> {
        return service.getTransactionDetail(transactionId)
                .compose(applySingleSchedulers())
    }


}