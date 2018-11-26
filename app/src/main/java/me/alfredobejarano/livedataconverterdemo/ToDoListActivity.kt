package me.alfredobejarano.livedataconverterdemo

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.alfredobejarano.livedataconverterdemo.adapter.ToDoAdapter
import me.alfredobejarano.livedataconverterdemo.utilities.Injector
import me.alfredobejarano.livedataconverterdemo.viewmodel.ToDoListViewModel

/**
 * Activity that displays a List of ToDo objects inside a RecyclerView.
 */
class ToDoListActivity : AppCompatActivity() {
    private var mLoadingDialog: ProgressDialog? = null
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
        // Sets the activity root as a simple progress bar.
        setContentView(R.layout.layout_loading)
        // Retrieve the To Do list.
        mViewModel.getList().observe(this, Observer { result ->
            // Sets the activity root as the RecyclerView.
            setContentView(mRootView)
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
                Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
