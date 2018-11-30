package me.alfredobejarano.livedataconverterdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*

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
