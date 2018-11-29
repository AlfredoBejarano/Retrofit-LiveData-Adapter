package me.alfredobejarano.livedataconverterdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alfredobejarano.livedataconverterdemo.data.ToDo
import me.alfredobejarano.livedataconverterdemo.data.ToDoRepository

/**
 *
 * ViewModel that intercepts UI events and uses a [repository class][ToDoRepository]
 * to provide data for the UI using observation via [LiveData] objects.
 *
 * @author Alfredo Bejarano
 * @since November 29, 2018 - 16:09
 * @version 1.0
 **/
class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {
    /**
     * Retrieves a ToDo from the cloud by its id.
     * @param toDoId Id of the ToDo object.
     * @return [LiveData] object containing the [ToDo].
     */
    fun fetchToDo(toDoId: Int): LiveData<ToDo> =
        repository.fetchToDo(toDoId)

    /**
     * Factory class that tells a LifeCycleOwner how to
     * create an instance of a [ToDoViewModel] class.
     */
    class Factory(private val repository: ToDoRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            ToDoViewModel(repository) as T
    }
}