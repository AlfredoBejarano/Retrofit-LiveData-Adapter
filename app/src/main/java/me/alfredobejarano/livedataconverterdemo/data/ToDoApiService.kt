package me.alfredobejarano.livedataconverterdemo.data

import androidx.lifecycle.LiveData
import me.alfredobejarano.retrofitadapters.data.ApiResult
import retrofit2.http.GET

/**
 *
 * API definitions for the Retrofit client.
 *
 * @author Alfredo Bejarano
 * @since November 25, 2018 - 20:34
 * @version 1.0
 **/
interface ToDoApiService {
    /**
     * Fetches the list of To Do objects from the server.
     */
    @GET("/todos")
    fun fetchTODOList(): LiveData<ApiResult<List<ToDo>>>
}