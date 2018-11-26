package me.alfredobejarano.retrofitlivedataadapter.data

/**
 *
 * Model class containing the results of a API call from Retrofit.
 *
 * @author Alfredo Bejarano
 * @since November 25, 2018 - 20:09
 * @version 1.0
 **/
data class ApiResult<T>(
    /**
     * HTTP code result from the call.
     */
    val code: Int,
    /**
     * Error that happened while executing the call. (if something went wrong).
     */
    val error: String?,
    /**
     * The result from the API call.
     */
    val body: T?
)