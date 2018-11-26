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

class ToDoListActivity : AppCompatActivity() {
    private lateinit var mRootView: RecyclerView
    private lateinit var mViewModel: ToDoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the root view.
        mRootView = RecyclerView(this).also {
            // Assign a layout manager while creating the root view.
            it.layoutManager = LinearLayoutManager(this)
        }
        // Retrieve a ViewModel for this activity using dependency inversion.
        mViewModel = ViewModelProviders.of(this, Injector.toDoListViewModelFactory)[ToDoListViewModel::class.java]
        // Sets the activity root as a RecyclerView.
        setContentView(mRootView)
        // Retrieve the To Do list.
        mViewModel.getList().observe(this, Observer {
            Log.d("LIST", it?.body?.toString() ?: it?.error?.localizedMessage ?: "Null")
        })
    }
}
