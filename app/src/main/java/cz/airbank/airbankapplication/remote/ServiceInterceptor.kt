package cz.airbank.airbankapplication.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Interceptor for http communication.
 *
 * @author Ivo Chvojka
 */

class ServiceInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest =
                request.newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
        return chain.proceed(newRequest)
    }
}