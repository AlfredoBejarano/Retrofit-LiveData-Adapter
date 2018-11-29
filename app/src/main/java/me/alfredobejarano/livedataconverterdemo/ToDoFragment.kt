package me.alfredobejarano.livedataconverterdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.alfredobejarano.livedataconverterdemo.databinding.FragmentToDoBinding
import me.alfredobejarano.livedataconverterdemo.utilities.Injector
import me.alfredobejarano.livedataconverterdemo.viewmodel.ToDoViewModel

/**
 *
 * Simple [Fragment] subclass that displays the title of a ToDo.
 *
 * @author Alfredo Bejarano
 * @since November 29, 2018 - 16:43
 * @version 1.0
 **/
class ToDoFragment : Fragment() {
    /**
     * reference to the loading view from the activity.
     */
    private lateinit var mLoading: ProgressBar
    /**
     * Factory class for creating instance of the view model for this class.
     */
    private val mViewModelFactory = Injector.toDoViewModelFactory
    /**
     * Reference to the ToDo id to display.
     */
    private var toDoId: Int = 0
    private lateinit var mBinding: FragmentToDoBinding

    /**
     * Creates the Root view from a given layout resource.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentToDoBinding.inflate(inflater)
        return mBinding.root
    }

    /**
     * After creating the view, proceeds to retrieve the ToDo using the ViewModel.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Set the binding lifecycle owner.
        mBinding.setLifecycleOwner(this)
        // Get the reference for the activity loading view.
        mLoading = requireActivity().findViewById(R.id.loading)
        // Retrieve the ToDo id from the saved state or the safeArgs if it was not yet saved.
        toDoId = savedInstanceState?.getInt("toDoId") ?: ToDoFragmentArgs.fromBundle(arguments).toDoId
        // Retrieve the ToDo using its id.
        ViewModelProviders.of(this, mViewModelFactory)[ToDoViewModel::class.java]
            .fetchToDo(toDoId).observe(this, Observer { toDo ->
                // Set the ToDo to the DataBinding or display a generic error message.
                toDo?.let {
                    mBinding.todo = it
                } ?: run {
                    Toast.makeText(requireContext(), R.string.oopsie, Toast.LENGTH_SHORT).show()
                }
            })
    }

    /**
     * Save the ToDo id to the saved instance state bundle.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("toDoId", toDoId)
        super.onSaveInstanceState(outState)
    }
}