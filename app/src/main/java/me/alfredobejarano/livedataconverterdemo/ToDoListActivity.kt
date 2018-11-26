package me.alfredobejarano.livedataconverterdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.alfredobejarano.livedataconverterdemo.utilities.Injector
import me.alfredobejarano.livedataconverterdemo.viewmodel.ToDoListViewModel

/**
 * Activity that displays a List of ToDo objects inside a RecyclerView.
 */
class ToDoListActivity : AppCompatActivity() {
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
     * Creates this activity, retrieves the ViewModel for this UI
     * controller and then proceeds to fetch the list of ToDo objects.
     *
     * @param savedInstanceState The saved state of a previous instance of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Execute the super method.
        super.onCreate(savedInstanceState)
        // Initialize the root view.
        mRootView = RecyclerView(this).also {
            // Assign a layout manager while creating the root view.
            it.layoutManager = LinearLayoutManager(this)
        }
        // Retrieve a ViewModel for this activity using dependency inversion.
        mViewModel = ViewModelProviders.of(this, mViewModelFactory)[ToDoListViewModel::class.java]
        // Sets the activity root as a RecyclerView.
        setContentView(mRootView)
        // Retrieve the To Do list.
        mViewModel.getList().observe(this, Observer {
            Log.d("LIST", it?.body?.toString() ?: it?.error?.localizedMessage ?: "Null")
        })
    }
}
