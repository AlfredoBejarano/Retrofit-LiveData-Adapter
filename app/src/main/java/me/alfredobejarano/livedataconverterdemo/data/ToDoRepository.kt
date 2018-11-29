package me.alfredobejarano.livedataconverterdemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import me.alfredobejarano.retrofitadapters.data.ApiResult

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

    /**
     * Fetches a single of To Do object by its id and
     * reports it via a [Transformation][Transformations.map].
     * @param id Id of the ToDo object.
     * @return [LiveData] containing the ToDo result.
     */
    fun fetchToDo(id: Int): LiveData<ToDo> =
        Transformations.map(dataSource.fetchTODO(id)) { it }
}