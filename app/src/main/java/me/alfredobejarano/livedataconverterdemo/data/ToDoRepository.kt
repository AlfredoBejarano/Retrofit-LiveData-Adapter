package me.alfredobejarano.livedataconverterdemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.alfredobejarano.livedataconverter.data.ApiResult

/**
 *
 * Repository class that serves as the single source
 * of truth for the [ToDo] model class.
 *
 * @author Alfredo Bejarano
 * @since November 25, 2018 - 21:06
 * @version 1.0
 **/
class ToDoRepository(private val dataSource: ToDoApiService) {

    /**
     * Fetches a list of To Do objects and reports them
     * via a [Transformation][Transformations.map].
     */
    fun fetchToDoList(): LiveData<ApiResult<List<ToDo>>> =
        Transformations.map(dataSource.fetchTODOList()) { it }
}