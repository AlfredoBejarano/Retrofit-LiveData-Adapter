package me.alfredobejarano.livedataconverterdemo.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        // Strike the text if the ToDo has been completed.
        if (element.completed) {
            bindComponent.title.paintFlags = bindComponent.title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    class ToDoViewHolder(val dataBinding: ItemTodoBinding, itemView: View) : RecyclerView.ViewHolder(itemView)
}