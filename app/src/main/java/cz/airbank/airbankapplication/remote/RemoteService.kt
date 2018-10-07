package cz.airbank.airbankapplication.remote

import cz.airbank.airbankapplication.model.Transaction
import cz.airbank.airbankapplication.model.TransactionDetail
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Service for communication with remote API.
 *
 * @author Ivo Chvojka
 */

interface RemoteService {

    @POST("transactions")
    fun getTransactions(): Flowable<List<Transaction>>

    @POST("transactions/{transactionId}")
    fun getTransactionDetail(@Path("transactionId") transactionId: Long): Single<TransactionDetail>

}