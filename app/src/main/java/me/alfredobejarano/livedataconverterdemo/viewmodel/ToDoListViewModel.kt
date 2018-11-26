package me.alfredobejarano.livedataconverterdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alfredobejarano.retrofitadapters.data.ApiResult
import me.alfredobejarano.livedataconverterdemo.data.ToDo
import me.alfredobejarano.livedataconverterdemo.data.ToDoRepository

/**
 *
 * ViewModel that intercepts UI events and uses a [repository class][ToDoRepository]
 * to provide data for the UI using observation via [LiveData] objects.
 *
 * @author Alfredo Bejarano
 * @since November 25, 2018 - 21:14
 * @version 1.0
 **/
class ToDoListViewModel(private val repository: ToDoRepository) : ViewModel() {
    /**
     * Retrieves the list of the To Do objects from the repository.
     * Uses a transformation to provide a [LiveData] object to the UI.
     */
    fun getList(): LiveData<ApiResult<List<ToDo>>> =
        repository.fetchToDoList()

    /**
     * Factory class that tells a LifeCycleOwner how to
     * create an instance of a [ToDoListViewModel] class.
     */
    class Factory(private val repository: ToDoRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            ToDoListViewModel(repository) as T
    }
}