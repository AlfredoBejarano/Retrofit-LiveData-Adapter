package me.alfredobejarano.livedataconverterdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.alfredobejarano.livedataconverterdemo.adapter.ToDoAdapter
import me.alfredobejarano.livedataconverterdemo.utilities.Injector
import me.alfredobejarano.livedataconverterdemo.viewmodel.ToDoListViewModel

/**
 *
 * Simple [Fragment] subclass that displays a list of ToDo in a RecylcreView.
 *
 * @author Alfredo Bejarano
 * @since November 29, 2018 - 16:28
 * @version 1.0
 **/
class ToDoListFragment : Fragment() {
    /**
     * Defines the root view of this activity.
     */
    private lateinit var mRootView: RecyclerView
    /**
     * Defines the ViewModel for this UI controller.
     */
    private lateinit var mViewModel: ToDoListViewModel
    /**
     * Uses dependency inversion for retrieving a ViewModel Factory for this UI controller ViewModel.
     */
    private val mViewModelFactory = Injector.toDoListViewModelFactory

    /**
     * Creates the RecyclerView that will hold the ToDo elements.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        RecyclerView(requireContext()).also {
            // Assign a layout manager while creating the root view.
            it.layoutManager = LinearLayoutManager(requireContext())
        }.also { mRootView = it }

    /**
     * Retrieves the list of ToDd objects after creating the root view.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Retrieve a ViewModel for this activity using dependency inversion.
        mViewModel = ViewModelProviders.of(this, mViewModelFactory)[ToDoListViewModel::class.java]
        // Retrieve the To Do list.
        mViewModel.getList().observe(this, Observer { result ->
            // Create an adapter for the view if the result body is not null.
            result.body?.let {
                // Parse the results as a MutableList.
                val results = it as MutableList
                // Sort the results alphabetically.
                results.sortWith(compareBy { todo -> todo.title })
                // Report the sorted results to the adapter.
                mRootView.adapter = ToDoAdapter(results)
            } ?: run {
                // Simple error handling, a better implementation can be made, this is just for the demo.
                Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
            }
        })
    }
}