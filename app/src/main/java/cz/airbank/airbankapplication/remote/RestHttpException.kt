package cz.airbank.airbankapplication.remote

import com.google.gson.GsonBuilder
import retrofit2.HttpException
import retrofit2.Response

/**
 * Exception representing HTTP exception.
 *
 * @author Ivo Chvojka
 */
class RestHttpException(response: Response<*>) : HttpException(response) {
    var responseError: Error = parseError()


    private fun parseError(): Error {
        val gson = GsonBuilder().create()
        val body = response().errorBody()?.string() ?: return Error()
        return try {
            gson.fromJson(body, Error::class.java)
        } catch (e: Exception) {
            Error()
        }
    }
}