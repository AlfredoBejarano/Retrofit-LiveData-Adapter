package me.alfredobejarano.livedataconverterdemo.data

import androidx.lifecycle.LiveData
import me.alfredobejarano.retrofitadapters.data.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path

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
    @GET("/todos/")
    fun fetchTODOList(): LiveData<ApiResult<List<ToDo>>>

    /**
     * Retrieves a single To Do by its id.
     */
    @GET("/todos/{todoId}/")
    fun fetchTODO(@Path("todoId") toDoId: Int): LiveData<ToDo>
}