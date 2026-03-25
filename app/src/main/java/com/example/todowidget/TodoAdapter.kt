package com.example.todowidget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(
    private val onItemClickListener: (Todo) -> Unit,
    private val onItemCompleteListener: (Todo) -> Unit,
    private val onItemDeleteListener: (Long) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todos = listOf<Todo>()
    private val dateFormat = SimpleDateFormat("MM/dd HH:mm", Locale.getDefault())

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val foreground: CardView = itemView.findViewById(R.id.foreground)
        val tvComplete: TextView = itemView.findViewById(R.id.tvComplete)
        val tvDelete: TextView = itemView.findViewById(R.id.tvDelete)

        fun bind(todo: Todo) {
            tvTitle.text = todo.title
            tvTime.text = dateFormat.format(Date(todo.createdAt))
            
            if (todo.isCompleted) {
                tvTitle.alpha = 0.5f
            } else {
                tvTitle.alpha = 1f
            }

            itemView.setOnClickListener {
                onItemClickListener(todo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.bind(todo)
        
        holder.tvComplete.setOnClickListener {
            onItemCompleteListener(todo)
        }
        
        holder.tvDelete.setOnClickListener {
            onItemDeleteListener(todo.id)
        }
    }

    override fun getItemCount(): Int = todos.size

    fun submitList(newTodos: List<Todo>) {
        todos = newTodos
        notifyDataSetChanged()
    }

    fun getTodoAt(position: Int): Todo = todos[position]
}