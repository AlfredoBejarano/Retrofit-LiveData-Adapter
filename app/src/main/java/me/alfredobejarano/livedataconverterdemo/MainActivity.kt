package me.alfredobejarano.livedataconverterdemo

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import me.alfredobejarano.livedataconverterdemo.adapter.ToDoAdapter
import me.alfredobejarano.livedataconverterdemo.utilities.Injector
import me.alfredobejarano.livedataconverterdemo.viewmodel.ToDoListViewModel

/**
 * Activity that displays a List of ToDo objects inside a RecyclerView.
 */
class MainActivity : AppCompatActivity() {
    /**
     * Creates this activity.
     *
     * @param savedInstanceState The saved state of a previous instance of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Allows back navigation to the nav host fragment.
     */
    override fun onSupportNavigateUp() = findNavController(nav_host_fragment).navigateUp()
}
