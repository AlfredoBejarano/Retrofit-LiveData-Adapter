package me.alfredobejarano.livedataconverterdemo.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import me.alfredobejarano.livedataconverterdemo.R
import me.alfredobejarano.livedataconverterdemo.ToDoFragmentArgs
import me.alfredobejarano.livedataconverterdemo.ToDoListFragmentDirections
import me.alfredobejarano.livedataconverterdemo.data.ToDo
import me.alfredobejarano.livedataconverterdemo.databinding.ItemTodoBinding


/**
 *
 * [Write your documentation here]
 *
 * @author Alfredo Bejarano
 * @since November 25, 2018 - 22:43
 * @version 1.0
 **/
class ToDoAdapter(private val elements: List<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    override fun getItemCount() = elements.size

    /**
     * Creates a [ToDoViewHolder] instance to be attached to this RecyclerView.
     * @param parent The parent RecyclerView.
     * @param viewType The type of layout for the created ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val bindingComponent = ItemTodoBinding.inflate(LayoutInflater.from(parent.context))
        return ToDoViewHolder(bindingComponent, bindingComponent.root)
    }

    /**
     * Attaches a recycled [ToDoViewHolder] to the RecyclerView.
     * @param holder The holder being attached.
     * @param position The position in which the holder is being attached/recycled.
     */
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        // Get the binding component from the ViewHolder.
        val bindComponent = holder.dataBinding
        // Get the element at the given position from the list.
        val element = elements[position]
        // Set the ToDo element to the component.
        bindComponent.toDo = element
        // Set the text with a strike paint when the todo is completed.
        if (element.completed) {
            bindComponent.title.alpha = 0.25f
            bindComponent.title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            bindComponent.title.alpha = 0.87f
            bindComponent.title.paintFlags = Paint.ANTI_ALIAS_FLAG
        }
        // Navigate to the ToDo fragment when clicking an item in the list.
        holder.itemView.setOnClickListener {
            ToDoListFragmentDirections.actionToDoListFragmentToToDoFragment(element.id).apply {
                // Retrieve the ViewHolder's context as an activity.
                val activity = holder.itemView.context as AppCompatActivity
                // Navigate to the ToDoFragment using safe args.
                Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(this)
            }
        }
    }

    class ToDoViewHolder(val dataBinding: ItemTodoBinding, itemView: View) : RecyclerView.ViewHolder(itemView)
}