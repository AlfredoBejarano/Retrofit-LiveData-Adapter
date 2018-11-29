package me.alfredobejarano.retrofitadapters

import androidx.lifecycle.LiveData
import me.alfredobejarano.retrofitadapters.data.ApiResult
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *
 * [CallAdapter] for using [LiveData] as return types for Retrofit API definitions without
 * the usage of the [ApiResult] class.
 *
 * @author Alfredo Bejarano
 * @since November 29, 2018 - 16:05
 * @version 1.0
 **/
class LazyLiveDataAdapter<T>(private val responseType: Type) : CallAdapter<T, LiveData<T>> {
    /**
     * Creates a [LiveData] from a reporting the result of a [Call] only if there are **active** observers
     * observing it.
     *
     * @param call The call object that is going to be executed.
     */
    override fun adapt(call: Call<T>): LiveData<T> = object : LiveData<T>() {
        /**
         * When this LiveData gets active, perform the call.
         */
        override fun onActive() {
            // Execute the super method.
            super.onActive()
            // Check if the cal has been already enqueued/executed.
            if (!call.isExecuted) {
                // If it hasn't, enqueue it.
                call.enqueue(object : Callback<T> {
                    /**
                     * Function that gets triggered when a failure in the call has been detected.
                     * @param call The call that failed its execution.
                     * @param t The [Throwable] that happened during the call execution.
                     */
                    override fun onFailure(call: Call<T>, t: Throwable) =
                    // Report an API result with a null body and the exception itself.
                        postValue(null)

                    /**
                     * This function gets triggered when a call has succeeded at an HTTP level
                     * (it returned a 20X code), the response can still be unsuccessful tho,
                     * so an additional check is made.
                     *
                     * @param call The call that "succeeded" its execution.
                     * @param response The response from said "successful" call.
                     */
                    override fun onResponse(call: Call<T>, response: Response<T>) =
                    // Check if the response is indeed, successful.
                        if (response.isSuccessful) {
                            // If so, report a new ApiResult instance with the HTTP code, a null error and the response body.
                            postValue(response.body())
                        } else {
                            // If it is not successful, report the HTTP code, and HTTPException with the response and an null body.
                            postValue(null)
                        }
                })
            } else {
                // If the call has been already executed, post the ApiResult with an Illegal exception detailing that the call has been already executed.
                postValue(null)
            }
        }
    }

    /**
     * Retrieves the [Type] of the desired response object.
     */
    override fun responseType() = responseType
}