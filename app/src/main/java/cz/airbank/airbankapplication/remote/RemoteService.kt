package cz.airbank.airbankapplication.remote

import cz.airbank.airbankapplication.model.TransactionDetail
import cz.airbank.airbankapplication.model.Transactions
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service for communication with remote API.
 *
 * @author Ivo Chvojka
 */

interface RemoteService {

    @GET("transactions")
    fun getTransactions(): Single<Transactions>

    @GET("transactions/{transactionId}")
    fun getTransactionDetail(@Path("transactionId") transactionId: Long): Single<TransactionDetail>

}